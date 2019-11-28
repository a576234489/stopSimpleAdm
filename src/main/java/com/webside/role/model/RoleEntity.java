package com.webside.role.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import com.webside.base.basemodel.BaseEntity;
import com.webside.plateformaccount.model.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Alias("roleEntity")
@ApiModel(value="角色模型")
public class RoleEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="角色名" ,required=false)
	@NotEmpty(message="角色名不能为空")
	private String name;
	@ApiModelProperty(value="角色key" ,required=false)
	@NotEmpty(message="角色标识不能为空")
	private String key;
	@ApiModelProperty(value="角色状态,0：正常；1：删除" ,required=false)
	private Integer status;
	@ApiModelProperty(value="角色描述" ,required=false)
	private String description;
	@ApiModelProperty(value="创建时间" ,required=false)
	private Date createTime;
	@ApiModelProperty(value="更新时间" ,required=false)
	private Date updateTime;
	@ApiModelProperty(value="包含的用户" ,required=false)
	private List<UserEntity> userList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public List<UserEntity> getUserList() {
		return userList;
	}
	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

}