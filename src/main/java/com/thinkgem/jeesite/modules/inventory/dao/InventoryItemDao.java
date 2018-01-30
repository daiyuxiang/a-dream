/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.inventory.entity.InventoryItem;

/**
 * 明细DAO接口
 * @author daiyuxiang
 * @version 2017-08-25
 */
@MyBatisDao
public interface InventoryItemDao extends CrudDao<InventoryItem> {
	public List<InventoryItem> findListByIds(String[] ids);
}