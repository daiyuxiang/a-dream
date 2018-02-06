/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.inventory.entity.Supplier;
import com.thinkgem.jeesite.modules.inventory.service.InventoryService;
import com.thinkgem.jeesite.modules.inventory.service.SupplierService;
import com.thinkgem.jeesite.modules.inventory.utils.InventoryEnum;
import com.thinkgem.jeesite.modules.inventory.utils.NoGen;
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
	private SupplierService supplierService;

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
	public String list(Inventory inventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Inventory> page = inventoryService.findPage(new Page<Inventory>(request, response), inventory);

		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_2.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);

		return "modules/inventory/outList";
	}

	@RequestMapping(value = "form")
	public String form(Inventory inventory, Model model) {
		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_2.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		model.addAttribute("inventory", inventory);
		model.addAttribute("supplierList", supplierList);

		return "modules/inventory/outForm";
	}

	@RequestMapping(value = "save")
	public String save(Inventory inventory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, inventory)) {
			return form(inventory, model);
		}

		if (inventory.getIsNewRecord()) {
			int no = inventoryService.count(inventory);
			inventory.setInventoryNo(NoGen.getNo("CK", no + 1));
		}

		inventoryService.save(inventory);
		addMessage(redirectAttributes, "保存出库单成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/out/form?id=" + inventory.getId();
	}

	@RequestMapping(value = "delete")
	public String delete(Inventory inventory, RedirectAttributes redirectAttributes) {
		inventoryService.delete(inventory);
		addMessage(redirectAttributes, "删除出库单成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/out/?repage";
	}

}
