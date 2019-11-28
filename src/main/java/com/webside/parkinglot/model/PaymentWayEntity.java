package com.webside.parkinglot.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("paymentWayEntity")
@ApiModel(value="停车场支付模型")
public class PaymentWayEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="支付方式" ,required=false)
	private String paymentName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	
	
}
