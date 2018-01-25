/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.inventory.entity.Order;

/**
 * 订单DAO接口
 * 
 * @author daiyuxiang
 * @version 2018-01-24
 */
@MyBatisDao
public interface OrderDao extends CrudDao<Order> {
	public List<Order> queryRemindList(Order order);
}