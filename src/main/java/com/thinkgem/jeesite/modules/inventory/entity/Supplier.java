/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.ConstantsUtil;

/**
 * 供应商Entity
 * 
 * @author daiyuxiang
 * @version 2017-08-21
 */
public class Supplier extends DataEntity<Supplier> {

	private static final long serialVersionUID = 1L;
	private String supplierCode; // 供应商编码
	private String supplierName; // 供应商名称
	private String dutyParagraph; // 税号
	private String bank; // 开户银行
	private String bankAccount; // 开户账号
	private String tel; // 电话
	private String address; // 地址
	private String type; // 类型 1供应商 2客户

	private String typeString; // 类型查询条件

	public Supplier() {
		super();
		this.type = ConstantsUtil.YES_FLAG;

	}

	public Supplier(String id) {
		super(id);
	}

	@Length(min = 0, max = 50, message = "供应商编码长度必须介于 0 和 50 之间")
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	@Length(min = 0, max = 50, message = "供应商名称长度必须介于 0 和 50 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Length(min = 0, max = 200, message = "税号长度必须介于 0 和 200 之间")
	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}

	@Length(min = 0, max = 200, message = "开户银行长度必须介于 0 和 200 之间")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Length(min = 0, max = 200, message = "开户账号长度必须介于 0 和 200 之间")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Length(min = 0, max = 50, message = "电话长度必须介于 0 和 50 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Length(min = 0, max = 200, message = "地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min = 0, max = 2, message = "类型 1供应商 2客户长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

}