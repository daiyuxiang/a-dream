/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.MessageBean;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inventory.entity.Brand;
import com.thinkgem.jeesite.modules.inventory.entity.InventoryItem;
import com.thinkgem.jeesite.modules.inventory.service.BrandService;
import com.thinkgem.jeesite.modules.inventory.service.InventoryItemService;
import com.thinkgem.jeesite.modules.inventory.service.InventoryService;
import com.thinkgem.jeesite.modules.inventory.utils.InventoryEnum;

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

	@Autowired
	private BrandService brandService;

	@ModelAttribute
	public InventoryItem get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String inventoryId) {
		InventoryItem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = inventoryItemService.get(id);
		}
		if (entity == null) {
			entity = new InventoryItem();
			if (StringUtils.isNotBlank(inventoryId)) {
				entity.setInventoryId(inventoryId);
			}
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public @ResponseBody Page<InventoryItem> list(InventoryItem inventoryItem, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Page<InventoryItem> page = new Page<InventoryItem>(request, response);

		if (StringUtils.isNotBlank(inventoryItem.getInventoryId())) {
			page = inventoryItemService.findPage(new Page<InventoryItem>(request, response), inventoryItem);
		}

		return page;
	}

	@RequestMapping(value = "form")
	public String form(InventoryItem inventoryItem, Model model) {
		Brand brandParam = new Brand();
		List<Brand> brandList = brandService.findList(brandParam);

		model.addAttribute("inventoryItem", inventoryItem);
		model.addAttribute("brandList", brandList);

		return "modules/inventory/inventoryItemForm";
	}
	
	@RequestMapping(value = "updatePrice")
	public String updatePrice(InventoryItem inventoryItem, Model model) {
		model.addAttribute("inventoryItem", inventoryItem);

		return "modules/inventory/priceForm";
	}

	@RequestMapping(value = "saveIn")
	public @ResponseBody MessageBean<String> saveIn(InventoryItem inventoryItem, Model model) {
		MessageBean<String> messageBen = new MessageBean<String>();

		if (!beanValidator(model, inventoryItem)) {
			messageBen.setStatus(MessageBean.ERROR);
			return messageBen;
		}

		if (inventoryItem.getIsNewRecord()) {
			inventoryItem.setGoodsType(InventoryEnum.INVENTORY_TYPE_1.getValue());
		}

		inventoryItemService.save(inventoryItem);

		// 修改主表总价
		inventoryService.updateTotalPrice(inventoryItem.getInventoryId());

		messageBen.setStatus(MessageBean.SUCCESS);
		return messageBen;
	}

	@RequestMapping(value = "saveOut")
	public @ResponseBody MessageBean<String> saveOut(@RequestBody(required = true) List<InventoryItem> outItemList,
			Model model) {
		MessageBean<String> messageBen = new MessageBean<String>();

		List<String> idList = new ArrayList<String>();

		for (InventoryItem outItem : outItemList) {
			idList.add(outItem.getOldId());
		}

		List<InventoryItem> inItemList = inventoryItemService.findListByIds(idList.toArray(new String[idList.size()]));
		Map<String, InventoryItem> inItemMap = new HashMap<String, InventoryItem>();

		for (InventoryItem inItem : inItemList) {
			inItemMap.put(inItem.getId(), inItem);
		}

		for (InventoryItem outItem : outItemList) {
			InventoryItem inItem = inItemMap.get(outItem.getOldId());

			outItem.setGoodsType(InventoryEnum.INVENTORY_TYPE_2.getValue());
			outItem.setDirection(inItem.getDirection());
			outItem.setFactoryNo(inItem.getFactoryNo());
			outItem.setGoodsBrand(inItem.getGoodsBrand());
			outItem.setGoodsArea(inItem.getGoodsArea());
			outItem.setGoodsName(inItem.getGoodsName());
			outItem.setGoodsSize(inItem.getGoodsSize());
			outItem.setGoodsWeight(inItem.getGoodsWeight());
			outItem.setLocation(inItem.getLocation());

			// 保存出库明细
			inventoryItemService.save(outItem);
			
			// 修改主表总价
			inventoryService.updateTotalPrice(outItem.getInventoryId());

			// 修改入库明细
			int inNum = Integer.valueOf(inItem.getNum());
			int outNum = Integer.valueOf(outItem.getNum());

			// 入库数量大于出库数量,有余量
			if (inNum > outNum) {
				InventoryItem newItem = (InventoryItem) inItem.clone();
				newItem.setId(null);
				newItem.setGoodsType(InventoryEnum.INVENTORY_TYPE_2.getValue());
				newItem.setNum(String.valueOf(outNum));
				inventoryItemService.save(newItem);

				inItem.setNum(String.valueOf(inNum - outNum));
				inventoryItemService.save(inItem);
				// 入库数量等于出库数量
			} else if (inNum == outNum) {
				inItem.setGoodsType(InventoryEnum.INVENTORY_TYPE_2.getValue());
				inventoryItemService.save(inItem);
			}

		}

		messageBen.setStatus(MessageBean.SUCCESS);
		return messageBen;
	}

	@RequestMapping(value = "deleteIn")
	public @ResponseBody MessageBean<String> deleteIn(InventoryItem inItem) {
		MessageBean<String> messageBen = new MessageBean<String>();

		inventoryItemService.delete(inItem);

		// 修改主表总价
		inventoryService.updateTotalPrice(inItem.getInventoryId());
		
		messageBen.setStatus(MessageBean.SUCCESS);

		return messageBen;
	}

	@RequestMapping(value = "deleteOut")
	public @ResponseBody MessageBean<String> deleteOut(InventoryItem outItem) {
		MessageBean<String> messageBen = new MessageBean<String>();

		inventoryItemService.delete(outItem);
		
		// 修改主表总价
		inventoryService.updateTotalPrice(outItem.getInventoryId());
		
		InventoryItem inItem = inventoryItemService.get(outItem.getOldId());
		inItem.setGoodsType(InventoryEnum.INVENTORY_TYPE_1.getValue());
		inventoryItemService.save(inItem);

		messageBen.setStatus(MessageBean.SUCCESS);

		return messageBen;
	}

	@RequestMapping(value = "selectIn")
	public String selectIn(InventoryItem inventoryItem, Model model) {

		model.addAttribute("inventoryItem", inventoryItem);
		return "modules/inventory/inventoryItemForm";
	}
}