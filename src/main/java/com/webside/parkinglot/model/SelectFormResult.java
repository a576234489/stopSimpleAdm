package com.webside.parkinglot.model;

import org.apache.ibatis.type.Alias;

import io.swagger.annotations.ApiModel;

@Alias("selectFormResult")
@ApiModel(value="下拉多选框返回值")
public class SelectFormResult {
	public String disabled;
	public String name;
	public String selected;
	public int value;
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
