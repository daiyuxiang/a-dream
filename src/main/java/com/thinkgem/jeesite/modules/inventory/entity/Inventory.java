/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 出入库Entity
 * @author daiyuxiang
 * @version 2017-08-23
 */
public class Inventory extends DataEntity<Inventory> {
	
	private static final long serialVersionUID = 1L;
	private String inventoryNo;		// 单号
	private String supplierId;		// 供应商ID
	private Date inventoryDate;		// 单号时间
	private String orderNo;		// 订单号
	private String otherOrderNo;		// 对方订单号
	private String totalPrice;		// 总价
	private String invoice;		// 发票
	private Date openDate;		// 开票日期
	private Date arrivalDate;		// 到票时间
	private String type;		// 类型：1入库 2出库
	
	private Date inventoryDateBegin;
	private Date inventoryDateEnd;
	
	public Inventory() {
		super();
	}

	public Inventory(String id){
		super(id);
	}

	@Length(min=0, max=100, message="单号长度必须介于 0 和 100 之间")
	public String getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(String inventoryNo) {
		this.inventoryNo = inventoryNo;
	}
	
	@Length(min=0, max=64, message="供应商ID长度必须介于 0 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	
	@Length(min=0, max=100, message="订单号长度必须介于 0 和 100 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=100, message="对方订单号长度必须介于 0 和 100 之间")
	public String getOtherOrderNo() {
		return otherOrderNo;
	}

	public void setOtherOrderNo(String otherOrderNo) {
		this.otherOrderNo = otherOrderNo;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=2, message="发票长度必须介于 0 和 2 之间")
	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@Length(min=0, max=2, message="类型：1入库 2出库长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getInventoryDateBegin() {
		return inventoryDateBegin;
	}

	public void setInventoryDateBegin(Date inventoryDateBegin) {
		this.inventoryDateBegin = inventoryDateBegin;
	}

	public Date getInventoryDateEnd() {
		return inventoryDateEnd;
	}

	public void setInventoryDateEnd(Date inventoryDateEnd) {
		this.inventoryDateEnd = inventoryDateEnd;
	}
	
	
}