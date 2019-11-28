package com.webside.message.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("userLongRentRecordEntity")
@ApiModel(value="用户长租卡办理记录模型")
public class UserLongRentRecordEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="停车场名称" ,required=false)
	private String carParkName;
	@ApiModelProperty(value="停车场编号" ,required=false)
	private String carParkNumber;
	@ApiModelProperty(value="用户昵称" ,required=false)
	private String nickName;
	@ApiModelProperty(value="车牌号码" ,required=false)
	private String carNumber;
	@ApiModelProperty(value="电话号码" ,required=false)
	private String telphone;
	@ApiModelProperty(value="1月卡 2季卡 3年卡" ,required=false)
	private Integer type;
	@ApiModelProperty(value="花费金额" ,required=false)
	private double handlingExpenses;
	@ApiModelProperty(value="总花费金额" ,required=false)
	private double totalHandlingExpenses;
	@ApiModelProperty(value="有效开始时间" ,required=false)
	private Date effectiveStartTime;
	@ApiModelProperty(value="截止时间" ,required=false)
	private Date expireTime;
	@ApiModelProperty(value="办理时间" ,required=false)
	private Date handleTime;
	
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarParkName() {
		return carParkName;
	}
	public void setCarParkName(String carParkName) {
		this.carParkName = carParkName;
	}
	public String getCarParkNumber() {
		return carParkNumber;
	}
	public void setCarParkNumber(String carParkNumber) {
		this.carParkNumber = carParkNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public double getHandlingExpenses() {
		return handlingExpenses;
	}
	public void setHandlingExpenses(double handlingExpenses) {
		this.handlingExpenses = handlingExpenses;
	}
	public double getTotalHandlingExpenses() {
		return totalHandlingExpenses;
	}
	public void setTotalHandlingExpenses(double totalHandlingExpenses) {
		this.totalHandlingExpenses = totalHandlingExpenses;
	}
	public Date getEffectiveStartTime() {
		return effectiveStartTime;
	}
	public void setEffectiveStartTime(Date effectiveStartTime) {
		this.effectiveStartTime = effectiveStartTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	
	
}
