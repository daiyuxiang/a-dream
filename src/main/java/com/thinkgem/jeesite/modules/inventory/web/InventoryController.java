/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
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

/**
 * 出入库Controller
 * @author daiyuxiang
 * @version 2017-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/inventory/inventory")
public class InventoryController extends BaseController {

	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private SupplierService supplierService;
	
	@ModelAttribute
	public Inventory get(@RequestParam(required=false) String id) {
		Inventory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = inventoryService.get(id);
		}
		if (entity == null){
			entity = new Inventory();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Inventory inventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Inventory> page = inventoryService.findPage(new Page<Inventory>(request, response), inventory); 
		
		Supplier supplierParam = new Supplier();
		List<Supplier> supplierList  = supplierService.findList(supplierParam);
		
		
		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);

		
		return "modules/inventory/inventoryList";
	}

	@RequestMapping(value = "form")
	public String form(Inventory inventory, Model model) {
		model.addAttribute("inventory", inventory);
		return "modules/inventory/inventoryForm";
	}

	@RequestMapping(value = "save")
	public String save(Inventory inventory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, inventory)){
			return form(inventory, model);
		}
		inventoryService.save(inventory);
		addMessage(redirectAttributes, "保存入库单成功");
		return "redirect:"+Global.getAdminPath()+"/inventory/inventory/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Inventory inventory, RedirectAttributes redirectAttributes) {
		inventoryService.delete(inventory);
		addMessage(redirectAttributes, "删除出入库成功");
		return "redirect:"+Global.getAdminPath()+"/inventory/inventory/?repage";
	}
	
	
}