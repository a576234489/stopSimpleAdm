package com.webside.plateformaccount.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import com.webside.role.model.RoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("userEntity")
@ApiModel(value="平台账号模型")
public class UserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="用户真实姓名" ,required=false)
	@NotEmpty(message="用户姓名不能为空")
	private String userName;
	@ApiModelProperty(value="这里账户名称统一使用邮箱" ,required=false)
	private String accountName;
	@ApiModelProperty(value="密码" ,required=false)
	private String password;
	@ApiModelProperty(value="电话" ,required=false)
	private String phone;
	@ApiModelProperty(value="逻辑删除状态：0：正常；1：删除" ,required=false)
	private Integer deleteStatus;
	@ApiModelProperty(value="是否锁定：0：正常；1：锁定" ,required=false)
	private Integer locked;
	@ApiModelProperty(value="描述" ,required=false)
	private String description;
	@ApiModelProperty(value="加密盐" ,required=false)
	private String credentialsSalt;
	@ApiModelProperty(value="创建者" ,required=false)
	private String creatorName;
	@ApiModelProperty(value="创建时间" ,required=false)
	private Date createTime;
	@ApiModelProperty(value="更新时间" ,required=false)
	private Date updateTime;
	@ApiModelProperty(value="角色信息" ,required=false)
	private RoleEntity role;
	@ApiModelProperty(value="用户详情" ,required=false)
	private UserInfoEntity userInfo;
	@ApiModelProperty(value="角色名称" ,required=false)
	private String roleName;
	public boolean isEnabled() {
		return locked == 0;
	}

	public boolean isForbidden() {
		return locked == null || locked == 1;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCredentialsSalt() {
		return credentialsSalt;
	}
	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public UserInfoEntity getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoEntity userInfo) {
		this.userInfo = userInfo;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserEntity(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.accountName = userEntity.getAccountName();
		this.password = userEntity.getPassword();
		this.deleteStatus = userEntity.getDeleteStatus();
		this.locked = userEntity.getLocked();
		this.description = userEntity.getDescription();
		this.credentialsSalt = userEntity.getCredentialsSalt();
		this.creatorName = userEntity.getCreatorName();
		this.createTime = userEntity.getCreateTime();
		this.updateTime = userEntity.getUpdateTime();
		this.role = userEntity.getRole();
		this.userInfo = userEntity.getUserInfo();
		this.roleName = userEntity.getRoleName();
	}
	public UserEntity() {

	}
	
}
