/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inventory.entity.InventoryItem;
import com.thinkgem.jeesite.modules.inventory.dao.InventoryItemDao;

/**
 * 明细Service
 * @author daiyuxiang
 * @version 2017-08-25
 */
@Service
@Transactional(readOnly = true)
public class InventoryItemService extends CrudService<InventoryItemDao, InventoryItem> {

	public InventoryItem get(String id) {
		return super.get(id);
	}
	
	public List<InventoryItem> findList(InventoryItem inventoryItem) {
		return super.findList(inventoryItem);
	}
	
	public Page<InventoryItem> findPage(Page<InventoryItem> page, InventoryItem inventoryItem) {
		return super.findPage(page, inventoryItem);
	}
	
	@Transactional(readOnly = false)
	public void save(InventoryItem inventoryItem) {
		super.save(inventoryItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(InventoryItem inventoryItem) {
		super.delete(inventoryItem);
	}
	
}