/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.inventory.entity.Brand;
import com.thinkgem.jeesite.modules.inventory.dao.BrandDao;

/**
 * 品牌Service
 * @author daiyuxiang
 * @version 2017-08-21
 */
@Service
@Transactional(readOnly = true)
public class BrandService extends CrudService<BrandDao, Brand> {

	public Brand get(String id) {
		return super.get(id);
	}
	
	public List<Brand> findList(Brand brand) {
		return super.findList(brand);
	}
	
	public Page<Brand> findPage(Page<Brand> page, Brand brand) {
		return super.findPage(page, brand);
	}
	
	@Transactional(readOnly = false)
	public void save(Brand brand) {
		super.save(brand);
	}
	
	@Transactional(readOnly = false)
	public void delete(Brand brand) {
		super.delete(brand);
	}
	
}