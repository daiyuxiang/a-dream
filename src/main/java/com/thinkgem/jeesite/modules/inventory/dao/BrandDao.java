/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.inventory.entity.Brand;

/**
 * 品牌DAO接口
 * @author daiyuxiang
 * @version 2017-08-21
 */
@MyBatisDao
public interface BrandDao extends CrudDao<Brand> {
	
}