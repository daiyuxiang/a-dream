/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.MessageBean;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inventory.entity.Inventory;
import com.thinkgem.jeesite.modules.inventory.entity.InventoryItem;
import com.thinkgem.jeesite.modules.inventory.service.InventoryItemService;
import com.thinkgem.jeesite.modules.inventory.service.InventoryService;

/**
 * 明细Controller
 * 
 * @author daiyuxiang
 * @version 2017-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/inventory/inventoryItem")
public class InventoryItemController extends BaseController {

	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private InventoryItemService inventoryItemService;

	@ModelAttribute
	public InventoryItem get(@RequestParam(required=false) String id,@RequestParam(required=false) String inventoryId) {
		InventoryItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = inventoryItemService.get(id);
		}
		if (entity == null){
			entity = new InventoryItem();
			if(StringUtils.isNotBlank(inventoryId)) {
				entity.setInventoryId(inventoryId);
			}
		}
		return entity;
	}
	
	@RequestMapping(value = { "list", "" })
	public @ResponseBody Page<InventoryItem> list(InventoryItem inventoryItem, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Page<InventoryItem> page = new Page<InventoryItem>(request, response);
		
		if(StringUtils.isNotBlank(inventoryItem.getInventoryId())) {
			page = inventoryItemService.findPage(new Page<InventoryItem>(request, response), inventoryItem);
		}
		
		return page;
	}

	@RequestMapping(value = "form")
	public String form(InventoryItem inventoryItem, Model model) {
		model.addAttribute("inventoryItem", inventoryItem);
		return "modules/inventory/inventoryItemForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public MessageBean<InventoryItem> save(InventoryItem inventoryItem, Model model, RedirectAttributes redirectAttributes) {
		MessageBean<InventoryItem> messageBen = new MessageBean<InventoryItem>();
		
		String inventoryId = inventoryItem.getInventoryId();
		
		//先新增入库单
		if(StringUtils.isBlank(inventoryId)) {
			Inventory inventory = new Inventory();
			inventoryService.save(inventory);
			inventoryId = inventory.getId();
		}
		
		if (!beanValidator(model, inventoryItem)) {
			messageBen.setStatus(MessageBean.ERROR);
			messageBen.setData(inventoryItem);
			return messageBen;
		}
		
		inventoryItemService.save(inventoryItem);
		messageBen.setStatus(MessageBean.SUCCESS);
		messageBen.setData(inventoryItem);
		return messageBen;
	}
	

	@RequestMapping(value = "save_bak")
	public String save_bak(InventoryItem inventoryItem, Model model, RedirectAttributes redirectAttributes) {
		String inventoryId = inventoryItem.getInventoryId();
		
		//先新增入库单
		if(StringUtils.isBlank(inventoryId)) {
			Inventory inventory = new Inventory();
			inventoryService.save(inventory);
			inventoryId = inventory.getId();
		}
		
		inventoryItemService.save(inventoryItem);
		addMessage(redirectAttributes, "保存入库明细成功");
		return "redirect:"+Global.getAdminPath()+"/inventory/inventory/?repage";
	}
	


	@RequestMapping(value = "delete")
	public String delete(InventoryItem inventoryItem, RedirectAttributes redirectAttributes) {
		inventoryItemService.delete(inventoryItem);
		addMessage(redirectAttributes, "删除明细成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/inventoryItem/?repage";
	}

}