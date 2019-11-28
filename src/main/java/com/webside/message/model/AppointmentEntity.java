package com.webside.message.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("appointmentEntity")
@ApiModel(value="预约查询模型")
public class AppointmentEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="预约id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="车牌号码" ,required=false)
	private String carNumber;
	@ApiModelProperty(value="预约时间" ,required=false)
	private Date time;
	@ApiModelProperty(value="延长时间" ,required=false)
	private Date extendTime;
	@ApiModelProperty(value="预约费用" ,required=false)
	private double price;
	@ApiModelProperty(value="延长预约费用" ,required=false)
	private double extendPrice;
	@ApiModelProperty(value="状态 1预约中 2取消预约 3延长预约 4按时预约已完成 5延长预约已完成" ,required=false)
	private Integer status;
	@ApiModelProperty(value="创建时间" ,required=false)
	private Date createTime;
	@ApiModelProperty(value="支付方式名称" ,required=false)
	private String paymentName;
	@ApiModelProperty(value="停车场名称" ,required=false)
	private String name;
	@ApiModelProperty(value="停车场编号" ,required=false)
	private String parkingLotNumber;
	@ApiModelProperty(value="总延长预约费用" ,required=false)
	private double totalExtendPrice;
	@ApiModelProperty(value="总预约费用" ,required=false)
	private double totalPrice;
	public double getTotalExtendPrice() {
		return totalExtendPrice;
	}
	public void setTotalExtendPrice(double totalExtendPrice) {
		this.totalExtendPrice = totalExtendPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getExtendTime() {
		return extendTime;
	}
	public void setExtendTime(Date extendTime) {
		this.extendTime = extendTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getExtendPrice() {
		return extendPrice;
	}
	public void setExtendPrice(double extendPrice) {
		this.extendPrice = extendPrice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParkingLotNumber() {
		return parkingLotNumber;
	}
	public void setParkingLotNumber(String parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}
	
}
