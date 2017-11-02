/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 品牌Entity
 * @author daiyuxiang
 * @version 2017-08-21
 */
public class Brand extends DataEntity<Brand> {
	
	private static final long serialVersionUID = 1L;
	private String brandCode;		// 品牌编码
	private String brandName;		// 品牌名称
	
	public Brand() {
		super();
	}

	public Brand(String id){
		super(id);
	}

	@Length(min=0, max=50, message="品牌编码长度必须介于 0 和 50 之间")
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	
	@Length(min=0, max=50, message="品牌名称长度必须介于 0 和 50 之间")
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
}