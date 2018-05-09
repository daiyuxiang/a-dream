/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.inventory.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inventory.entity.Inventory;
import com.thinkgem.jeesite.modules.inventory.entity.InventoryItem;
import com.thinkgem.jeesite.modules.inventory.entity.Supplier;
import com.thinkgem.jeesite.modules.inventory.service.InventoryItemService;
import com.thinkgem.jeesite.modules.inventory.service.InventoryService;
import com.thinkgem.jeesite.modules.inventory.service.SupplierService;
import com.thinkgem.jeesite.modules.inventory.utils.InventoryEnum;
import com.thinkgem.jeesite.modules.inventory.utils.NoGen;
import com.thinkgem.jeesite.modules.inventory.vo.InventoryItemVO;
import com.thinkgem.jeesite.modules.inventory.vo.InventoryVO;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 出库Controller
 * 
 * @author daiyuxiang
 */
@Controller
@RequestMapping(value = "${adminPath}/inventory/out/")
public class OutController extends BaseController {

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InventoryItemService inventoryItemService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private OfficeService officeService;

	@ModelAttribute
	public Inventory get(@RequestParam(required = false) String id) {
		Inventory entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = inventoryService.get(id);
		}
		if (entity == null) {
			entity = new Inventory();
			entity.setType(InventoryEnum.INVENTORY_TYPE_2.getValue());
			if (!"1".equals(UserUtils.getUser().getUserType())) {
				entity.setCompanyId(UserUtils.getUser().getCompany().getId());
			}
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Inventory inventory, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Inventory> page = inventoryService.findPage(new Page<Inventory>(
				request, response), inventory);

		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(new String[] {
				InventoryEnum.SUPPLIER_TYPE_2.getValue(),
				InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService
				.findMinList(supplierParam);

		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);

		return "modules/inventory/outList";
	}

	@RequestMapping(value = "form")
	public String form(Inventory inventory, Model model) {
		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(new String[] {
				InventoryEnum.SUPPLIER_TYPE_2.getValue(),
				InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService
				.findMinList(supplierParam);

		model.addAttribute("inventory", inventory);
		model.addAttribute("supplierList", supplierList);

		return "modules/inventory/outForm";
	}

	@RequestMapping(value = "save")
	public String save(Inventory inventory, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, inventory)) {
			return form(inventory, model);
		}

		if (inventory.getIsNewRecord()) {
			int no = inventoryService.count(inventory);
			inventory.setInventoryNo(NoGen.getNo("CK", no + 1));
		}

		inventoryService.save(inventory);
		addMessage(redirectAttributes, "保存出库单成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/out/form?id="
				+ inventory.getId();
	}

	@RequestMapping(value = "delete")
	public String delete(Inventory inventory,
			RedirectAttributes redirectAttributes) {

		InventoryItem itemParam = new InventoryItem();
		itemParam.setInventoryId(inventory.getId());
		List<InventoryItem> itemList = inventoryItemService.findList(itemParam);

		if (CollectionUtils.isEmpty(itemList)) {
			inventoryService.delete(inventory);
			addMessage(redirectAttributes, "删除出库单成功");
		} else {
			addMessage(redirectAttributes, "出库单已经存在明细,不能删除");
		}

		return "redirect:" + Global.getAdminPath() + "/inventory/out/?repage";
	}

	@RequestMapping(value = "showPrint")
	public String showPrint(Inventory inventory, Model model) {
		Office company = UserUtils.getUser().getCompany();
		company = officeService.get(company.getId());

		Supplier supplier = supplierService.get(inventory.getSupplierId());

		InventoryVO inventoryVO = new InventoryVO();
		inventoryVO.setSupplierName(supplier.getSupplierName());
		inventoryVO.setCompanyName(company.getName());
		inventoryVO.setInventoryDate(inventory.getInventoryDate());
		inventoryVO.setOrderNo(inventory.getOrderNo());
		inventoryVO.setInventoryNo(inventory.getInventoryNo());

		inventoryVO.setAddress(company.getAddress());
		inventoryVO.setPhone(company.getPhone());
		inventoryVO.setPrintDate(DateUtils.getDate());

		BigDecimal sumTotalPriceD = new BigDecimal("0");
		BigDecimal sumTotalTaxD = new BigDecimal("0");
		BigDecimal sumTaxPriceD = new BigDecimal("0");
		
		InventoryItem param = new InventoryItem();
		param.setInventoryId(inventory.getId());
		List<InventoryItem> inventoryItemList = inventoryItemService
				.findList(param);

		List<InventoryItemVO> inventoryItemVOList = new ArrayList<InventoryItemVO>();
		String taxRate = "16%";

		for (InventoryItem inventoryItem : inventoryItemList) {
			InventoryItemVO inventoryItemVO = new InventoryItemVO();
			inventoryItemVO.setGoodsName(inventoryItem.getGoodsName());
			inventoryItemVO.setNum(inventoryItem.getNum());
			inventoryItemVO.setPrice(inventoryItem.getPrice());

			BigDecimal numD = new BigDecimal(inventoryItem.getNum());
			BigDecimal priceD = new BigDecimal(inventoryItem.getPrice());
			BigDecimal totalPriceD = numD.multiply(priceD);
			
			sumTotalPriceD = sumTotalPriceD.add(totalPriceD);
			inventoryItemVO.setTotalPrice(totalPriceD.toString());

			inventoryItemVO.setTaxRate(taxRate);

			BigDecimal totalTaxD = totalPriceD
					.divide(new BigDecimal("1.16"), 2);

			sumTotalTaxD = sumTotalTaxD.add(totalTaxD);
			inventoryItemVO.setTotalTax(totalTaxD.toString());

			BigDecimal taxPrice = totalPriceD.subtract(totalTaxD);

			sumTaxPriceD = sumTaxPriceD.add(taxPrice);
			inventoryItemVO.setTaxPrice(taxPrice.toString());

			inventoryItemVOList.add(inventoryItemVO);
		}

		inventoryVO.setSumTotalPrice(sumTotalPriceD.toString());
		inventoryVO.setSumTotalTax(sumTotalTaxD.toString());
		inventoryVO.setSumTaxPrice(sumTaxPriceD.toString());
		
		model.addAttribute("supplier", supplier);
		model.addAttribute("inventoryVO", inventoryVO);
		model.addAttribute("inventoryItemVOList", inventoryItemVOList);

		return "modules/inventory/showPrint";
	}
}
