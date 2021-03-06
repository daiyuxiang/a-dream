/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.inventory.web;

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
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 入库Controller
 * 
 * @author daiyuxiang
 */
@Controller
@RequestMapping(value = "${adminPath}/inventory/in/")
public class InController extends BaseController {

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InventoryItemService inventoryItemService;

	@Autowired
	private SupplierService supplierService;

	@ModelAttribute
	public Inventory get(@RequestParam(required = false) String id) {
		Inventory entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = inventoryService.get(id);
		}
		if (entity == null) {
			entity = new Inventory();
			entity.setType(InventoryEnum.INVENTORY_TYPE_1.getValue());
			if (!"1".equals(UserUtils.getUser().getUserType())) {
				entity.setCompanyId(UserUtils.getUser().getCompany().getId());
			}
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Inventory inventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Inventory> page = inventoryService.findPage(new Page<Inventory>(request, response), inventory);

		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_1.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);

		return "modules/inventory/inList";
	}

	@RequestMapping(value = "form")
	public String form(Inventory inventory, Model model) {
		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_1.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		model.addAttribute("inventory", inventory);
		model.addAttribute("supplierList", supplierList);

		return "modules/inventory/inForm";
	}

	@RequestMapping(value = "save")
	public String save(Inventory inventory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, inventory)) {
			return form(inventory, model);
		}

		if (inventory.getIsNewRecord()) {
			int no = inventoryService.count(inventory);
			inventory.setInventoryNo(NoGen.getNo("RK", no + 1));
		}

		inventoryService.save(inventory);
		addMessage(redirectAttributes, "保存入库单成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/in/form?id=" + inventory.getId();
	}

	@RequestMapping(value = "delete")
	public String delete(Inventory inventory, RedirectAttributes redirectAttributes) {

		InventoryItem itemParam = new InventoryItem();
		itemParam.setInventoryId(inventory.getId());
		List<InventoryItem> itemList = inventoryItemService.findList(itemParam);

		if (CollectionUtils.isEmpty(itemList)) {
			inventoryService.delete(inventory);
			addMessage(redirectAttributes, "删除入库单成功");
		} else {
			addMessage(redirectAttributes, "入库单已经存在明细,不能删除");
		}

		return "redirect:" + Global.getAdminPath() + "/inventory/in/?repage";
	}

}
