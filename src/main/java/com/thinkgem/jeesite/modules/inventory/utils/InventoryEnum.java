package com.thinkgem.jeesite.modules.inventory.utils;

public enum InventoryEnum {
	SUPPLIER_TYPE_1("1", "success"),
	SUPPLIER_TYPE_2("2","success");
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
