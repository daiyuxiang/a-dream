/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.vo;

import java.util.Date;

/**
 * 出入库Entity
 * 
 * @author daiyuxiang
 * @version 2017-08-23
 */
public class InventoryVO {

	private String supplierName;
	private String companyName;
	private Date inventoryDate;
	private String orderNo;
	private String inventoryNo;

	private String address;
	private String phone;
	private String printDate;

	private String sumTotalPrice;
	private String sumTotalTax;
	private String sumTaxPrice;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(String inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getSumTotalPrice() {
		return sumTotalPrice;
	}

	public void setSumTotalPrice(String sumTotalPrice) {
		this.sumTotalPrice = sumTotalPrice;
	}

	public String getSumTotalTax() {
		return sumTotalTax;
	}

	public void setSumTotalTax(String sumTotalTax) {
		this.sumTotalTax = sumTotalTax;
	}

	public String getSumTaxPrice() {
		return sumTaxPrice;
	}

	public void setSumTaxPrice(String sumTaxPrice) {
		this.sumTaxPrice = sumTaxPrice;
	}

}