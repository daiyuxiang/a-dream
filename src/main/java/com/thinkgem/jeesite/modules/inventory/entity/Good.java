/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 出入库Entity
 * 
 * @author daiyuxiang
 * @version 2017-08-23
 */
public class Good extends DataEntity<Good> {

	private static final long serialVersionUID = 1L;
	private String inventoryNo; // 单号
	private String supplierId; // 供应商ID
	private Date inventoryDate; // 单号时间
	private String orderNo; // 订单号
	private String otherOrderNo; // 对方订单号
	private String totalPrice; // 总价
	private String invoice; // 发票
	private Date openDate; // 开票日期
	private Date arrivalDate; // 到票时间
	private String type; // 主表类型：1入库 2出库
	private String companyId; // 公司ID

	private String inventoryItemId; // 明细表ID
	private String goodsName; // 产品名称
	private String goodsArea; // 产品产地
	private String factoryNo; // 出厂编号
	private String goodsSize; // 产品尺寸
	private String goodsWeight; // 产品重量
	private String num; // 数量
	private String price; // 单价
	private String direction; // 方向
	private String location; // 地点
	private String goodsType; // 货物状态:1在库 2出库

	private Date inventoryDateBegin;
	private Date inventoryDateEnd;

	private String supplierName; // 供应商名称
	private String companyName; //公司名称

	public Good() {
		super();
	}

	public Good(String id) {
		super(id);
	}

	public String getInventoryNo() {
		return inventoryNo;
	}

	public void setInventoryNo(String inventoryNo) {
		this.inventoryNo = inventoryNo;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsArea() {
		return goodsArea;
	}

	public void setGoodsArea(String goodsArea) {
		this.goodsArea = goodsArea;
	}

	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
}