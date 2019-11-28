package com.webside.message.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.webside.parkinglot.model.ParkingLotType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("menuEntity")
@ApiModel(value="信息查询模型")
public class MessageEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="订单id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="停车场名称" ,required=false)
	private String carParkName;
	@ApiModelProperty(value="停车场编号" ,required=false)
	private String carParkNumber;
	@ApiModelProperty(value="车牌号码" ,required=false)
	private String carNumber;
	@ApiModelProperty(value="停车场类型" ,required=false)
	private List<ParkingLotType> parkingLotTypes;
	@ApiModelProperty(value="入场时间" ,required=false)
	private Date admissionTime;
	@ApiModelProperty(value="出场时间" ,required=false)
	private Date exitTime;
	@ApiModelProperty(value="停车时长" ,required=false)
	private String stopDuration;
	@ApiModelProperty(value="长租卡类型" ,required=false)
	private Integer longRentType;
	@ApiModelProperty(value="实际付款金额" ,required=false)
	private Double amount;
	@ApiModelProperty(value="抵扣金额" ,required=false)
	private Double deductionAmount;
	@ApiModelProperty(value="商家减免金额" ,required=false)
	private Double businessAmount;
	@ApiModelProperty(value="订单号" ,required=false)
	private String orderNumber;
	@ApiModelProperty(value="总抵扣金额" ,required=false)
	private Double totalDeductionAmount;
	@ApiModelProperty(value="总商家减免金额" ,required=false)
	private Double totalBusinessAmount;
	@ApiModelProperty(value="总实际付款金额" ,required=false)
	private Double totalAmount;
	@ApiModelProperty(value="总应收" ,required=false)
	private Double totalReceivables;
	@ApiModelProperty(value="总优惠" ,required=false)
	private Double totalfavourable;
	@ApiModelProperty(value="支付方式id" ,required=false)
	private Integer paymentWayId;
	@ApiModelProperty(value="支付方式名称" ,required=false)
	private String paymentWayName;
	@ApiModelProperty(value="长租卡的过期时间" ,required=false)
	private Date expireTime;
	
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Double getTotalDeductionAmount() {
		return totalDeductionAmount;
	}
	public void setTotalDeductionAmount(Double totalDeductionAmount) {
		this.totalDeductionAmount = totalDeductionAmount;
	}
	public Double getTotalBusinessAmount() {
		return totalBusinessAmount;
	}
	public void setTotalBusinessAmount(Double totalBusinessAmount) {
		this.totalBusinessAmount = totalBusinessAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getPaymentWayId() {
		return paymentWayId;
	}
	public void setPaymentWayId(Integer paymentWayId) {
		this.paymentWayId = paymentWayId;
	}
	public String getPaymentWayName() {
		return paymentWayName;
	}
	public void setPaymentWayName(String paymentWayName) {
		this.paymentWayName = paymentWayName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getDeductionAmount() {
		return deductionAmount;
	}
	public void setDeductionAmount(Double deductionAmount) {
		this.deductionAmount = deductionAmount;
	}
	public Double getBusinessAmount() {
		return businessAmount;
	}
	public void setBusinessAmount(Double businessAmount) {
		this.businessAmount = businessAmount;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Double getTotalReceivables() {
		return totalReceivables;
	}
	public void setTotalReceivables(Double totalReceivables) {
		this.totalReceivables = totalReceivables;
	}
	public Double getTotalfavourable() {
		return totalfavourable;
	}
	public void setTotalfavourable(Double totalfavourable) {
		this.totalfavourable = totalfavourable;
	}
	public Date getExitTime() {
		return exitTime;
	}
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
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
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Integer getLongRentType() {
		return longRentType;
	}
	public void setLongRentType(Integer longRentType) {
		this.longRentType = longRentType;
	}
	public List<ParkingLotType> getParkingLotTypes() {
		return parkingLotTypes;
	}
	public void setParkingLotTypes(List<ParkingLotType> parkingLotTypes) {
		this.parkingLotTypes = parkingLotTypes;
	}
	public Date getAdmissionTime() {
		return admissionTime;
	}
	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}
	public String getStopDuration() {
		return stopDuration;
	}
	public void setStopDuration(String stopDuration) {
		this.stopDuration = stopDuration;
	}
	
	
	
}
