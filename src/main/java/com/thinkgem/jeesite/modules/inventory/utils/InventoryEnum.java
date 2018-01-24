package com.thinkgem.jeesite.modules.inventory.utils;

public enum InventoryEnum {

	Inventory_TYPE_1("1", "入库"), Inventory_TYPE_2("2", "出库"),
	SUPPLIER_TYPE_1("1", "供应商"), SUPPLIER_TYPE_2("2", "客户"), SUPPLIER_TYPE_3("3", "供应商+客户");
	private String value;
	private String label;

	InventoryEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
