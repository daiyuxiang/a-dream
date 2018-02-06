/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 明细Entity
 * 
 * @author daiyuxiang
 * @version 2017-08-25
 */
public class InventoryItem extends DataEntity<InventoryItem> implements Cloneable {

	private static final long serialVersionUID = 1L;
	private String inventoryId; // 主表ID
	private String goodsName; // 产品名称
	private String goodsBrand; // 产品品牌
	private String goodsArea; // 产品产地
	private String factoryNo; // 出厂编号
	private String goodsSize; // 产品尺寸
	private String goodsWeight; // 产品重量
	private String num; // 数量
	private String price; // 单价
	private String direction; // 方向
	private String location; // 地点
	private String goodsType; // 货物状态:1在库 2出库
	private String oldId; // 原物资Id
	private String imageUrl; // 图片地址
	private String barcode; // 条形码
	
	private String brandName; // 品牌名称

	public InventoryItem() {
		super();
	}

	public InventoryItem(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "主表ID长度必须介于 0 和 64 之间")
	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	@Length(min = 0, max = 100, message = "产品名称长度必须介于 0 和 100 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Length(min = 0, max = 100, message = "产品产地长度必须介于 0 和 100 之间")
	public String getGoodsArea() {
		return goodsArea;
	}

	public void setGoodsArea(String goodsArea) {
		this.goodsArea = goodsArea;
	}

	@Length(min = 0, max = 100, message = "出厂编号长度必须介于 0 和 100 之间")
	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	@Length(min = 0, max = 100, message = "产品尺寸长度必须介于 0 和 100 之间")
	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	@Length(min = 0, max = 100, message = "产品重量长度必须介于 0 和 100 之间")
	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	@Length(min = 0, max = 11, message = "数量长度必须介于 0 和 11 之间")
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

	@Length(min = 0, max = 100, message = "方向长度必须介于 0 和 100 之间")
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Length(min = 0, max = 100, message = "地点长度必须介于 0 和 100 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Length(min = 0, max = 2, message = "货物状态:1在库 2出库长度必须介于 0 和 2 之间")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	@Override
	public Object clone() {
		InventoryItem item = null;
		try {
			item = (InventoryItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return item;
	}

	public String getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
