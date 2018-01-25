package com.thinkgem.jeesite.modules.inventory.utils;

public enum OrderEnum {

	ORDER_STATUS_0("0", "未处理"), ORDER_STATUS_1("1", "处理完成");
	private String value;
	private String label;

	OrderEnum(String value, String label) {
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
