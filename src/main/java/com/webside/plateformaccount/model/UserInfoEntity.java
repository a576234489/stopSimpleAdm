package com.webside.plateformaccount.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.webside.base.basemodel.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: UserInfoEntity
 * @Description: 用户基本信息
 * @author gaogang
 * @date 2016年7月12日 下午2:38:43
 *
 */
@Alias("userInfoEntity")
@ApiModel(value="平台账号详情模型")
public class UserInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="性别" ,required=false)
	private Integer sex;
	@ApiModelProperty(value="出生日期" ,required=false)
	private Date birthday;
	@ApiModelProperty(value="手机" ,required=false)
	private String telephone;
	@ApiModelProperty(value="邮箱" ,required=false)
	private String email;
	@ApiModelProperty(value="联系地址" ,required=false)
	private String address;
	@ApiModelProperty(value="添加日期时间" ,required=false)
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
