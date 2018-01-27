/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inventory.dao.GoodDao;
import com.thinkgem.jeesite.modules.inventory.entity.Good;

/**
 * 货物Service
 * 
 * @author daiyuxiang
 * @version 2017-08-23
 */
@Service
@Transactional(readOnly = true)
public class GoodService extends CrudService<GoodDao, Good> {

	public Page<Good> findPage(Page<Good> page, Good good) {
		return super.findPage(page, good);
	}

}