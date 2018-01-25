/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单Entity
 * @author daiyuxiang
 * @version 2018-01-24
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单号
	private String goodName;		// 产品名称
	private String goodBrand;		// 产品品牌
	private String supplierId;		// 订单下达单位ID
	private Date arrivalDate;		// 预计到货时间
	private String shippingType;		// 运输方式
	private String num;		// 数量
	private String price;		// 单价
	private String customerId;		// 订单交付单位ID
	private Date productDate;		// 订单交付单位的货期
	private String orderStatus;		// 订单状态：0未处理 1处理完成
	private Date reminderDate;	//提醒时间
	
	private String goodBrandName;		// 产品品牌 名称
	private String supplierName;	// 订单下达单位名称
	private String customerName;	// 订单交付单位名称
	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	@Length(min=0, max=100, message="订单号长度必须介于 0 和 100 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=100, message="产品名称长度必须介于 0 和 100 之间")
	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	
	@Length(min=0, max=64, message="产品品牌长度必须介于 0 和 64 之间")
	public String getGoodBrand() {
		return goodBrand;
	}

	public void setGoodBrand(String goodBrand) {
		this.goodBrand = goodBrand;
	}
	
	@Length(min=0, max=64, message="订单下达单位ID长度必须介于 0 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@Length(min=0, max=50, message="运输方式长度必须介于 0 和 50 之间")
	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=64, message="订单交付单位ID长度必须介于 0 和 64 之间")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	
	@Length(min=0, max=2, message="订单状态：0未处理 1处理完成长度必须介于 0 和 2 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGoodBrandName() {
		return goodBrandName;
	}

	public void setGoodBrandName(String goodBrandName) {
		this.goodBrandName = goodBrandName;
	}

	public Date getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}
	
	
}