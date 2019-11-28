package com.webside.department.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DepartmentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Long parentId;

	private String parentName;

	private Date createDate;

	List<DepartmentEntity> subDepas;
	/*
	 * 是否叶子节点
	 */
	private boolean isLeaf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<DepartmentEntity> getSubDepas() {
		return subDepas;
	}

	public void setSubDepas(List<DepartmentEntity> subDepas) {
		this.subDepas = subDepas;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}
