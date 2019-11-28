package com.webside.parkinglot.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("parkingLotPayment")
@ApiModel(value="停车场支付中间模型")
public class ParkingLotPayment implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="停车场id" ,required=false)
	private Integer parkingLotId;
	@ApiModelProperty(value="支付方式id" ,required=false)
	private Integer paymentTypeId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Integer parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	
	
}
