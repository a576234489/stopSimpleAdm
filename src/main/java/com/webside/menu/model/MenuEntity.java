package com.webside.menu.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("menuEntity")
@ApiModel(value="菜单模型")
public class MenuEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="父节点ID" ,required=false)
	private Integer parentId;
	@ApiModelProperty(value="权限名称" ,required=false)
	@NotEmpty(message="资源名称不能为空")
	private String name;
	@ApiModelProperty(value="资源标识key" ,required=false)
	private String sourceKey;
	@ApiModelProperty(value="类型：0：菜单；1：按钮" ,required=false)
	private Integer type;
	@ApiModelProperty(value="菜单URL" ,required=false)
	private String sourceUrl;
	@ApiModelProperty(value="菜单的展开层级(暂不用)" ,required=false)
	private Integer level;
	@ApiModelProperty(value="菜单的图标" ,required=false)
	private String icon;
	@ApiModelProperty(value="是否隐藏" ,required=false)
	private Integer isHide;
	@ApiModelProperty(value="资源描述" ,required=false)
	private String description;
	@ApiModelProperty(value="资源创建时间" ,required=false)
	private Date createTime;
	@ApiModelProperty(value="资源更新时间" ,required=false)
	private Date updateTime;
	@ApiModelProperty(value="节点是否展开" ,required=false)
	private boolean isExpanded;
	@ApiModelProperty(value="是否叶子节点" ,required=false)
	private boolean isLeaf;
	@ApiModelProperty(value="是否加载完成" ,required=false)
	private boolean loaded = true;
	@ApiModelProperty(value="父节点名称" ,required=false)
	private String parentName;
	@ApiModelProperty(value="是否被选中" ,required=false)
	private boolean selected;
	@ApiModelProperty(value="是否展开" ,required=false)
	private boolean isOpen;
	@JsonProperty(value = "isOpen")
	public boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSourceKey() {
		return sourceKey;
	}
	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getIsHide() {
		return isHide;
	}
	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public boolean isExpanded() {
		return isExpanded;
	}
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
