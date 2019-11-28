package com.webside.parkinglot.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("parkingLotEntity")
@ApiModel(value="停车场模型")
public class ParkingLotEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="停车场编号" ,required=false)
	private String parkingLotNumber;
	@NotEmpty(message="停车场名称不能为空")
	@ApiModelProperty(value="停车场名称" ,required=false)
	private String name;
	@ApiModelProperty(value="停车场地址" ,required=false)
	@NotEmpty(message="停车场地址不能为空")
	private String address;
	@ApiModelProperty(value="经度" ,required=false)
	@NotEmpty(message="经度不能为空")
	private String longitude;
	@ApiModelProperty(value="纬度" ,required=false)
	@NotEmpty(message="纬度不能为空")
	private String latitude;
	@ApiModelProperty(value="省" ,required=false)
	@NotEmpty(message="省不能为空")
	private String province;
	@ApiModelProperty(value="市" ,required=false)
	@NotEmpty(message="市不能为空")
	private String city;
	@ApiModelProperty(value="区" ,required=false)
	@NotEmpty(message="区不能为空")
	private String area;
	@ApiModelProperty(value="是否支持长租办理 1有 2没有" ,required=false)
	@NotNull(message="长租办理选择不能为空")
	private Integer isLongrent;
	@ApiModelProperty(value="是否有充电桩 1有 2没有" ,required=false)
	@NotNull(message="充电桩选择不能为空")
	private Integer isChargingPile;
	@ApiModelProperty(value="停车场banner图64位数组" ,required=false)
	@NotEmpty(message="停车场banner图不能为空")
	private String imgs[];
	@ApiModelProperty(value="停车场banner图Url" ,required=false)
	private String imageUrls;
	@ApiModelProperty(value="充电桩总数量" ,required=false)
	private Integer totalChargingPileNumber;
	@ApiModelProperty(value="剩余充电桩数量" ,required=false)
	private Integer surplusChargingPileNumber;
	@ApiModelProperty(value="总车位" ,required=false)
	@NotNull(message="总车位不能为空")
	private Integer totalParkNumber;
	@ApiModelProperty(value="剩余车位" ,required=false)
	private Integer surplusParkNumber;
	@ApiModelProperty(value="开业时间" ,required=false)
	@NotEmpty(message="开业时间不能为空")
	private String openTime;
	@ApiModelProperty(value="结业时间" ,required=false)
	@NotEmpty(message="结业时间不能为空")
	private String endTime;
	@ApiModelProperty(value="停车价格 价格/小时" ,required=false)
	@NotNull(message="停车价格 不能为空")
	private BigDecimal price;
	@ApiModelProperty(value="预约价格" ,required=false)
	@NotNull(message="预约价格不能为空")
	private BigDecimal appointPrice;
	@ApiModelProperty(value="停车场类型id" ,required=false)
	@NotEmpty(message="停车场类型不能为空")
	private String parkingLotTypeId;
	@ApiModelProperty(value="停车场支付方式" ,required=false)
	@NotEmpty(message="停车场支付不能为空")
	private String paymentWay;
	@ApiModelProperty(value="说明" ,required=false)
	@NotEmpty(message="说明不能为空")
	private String remark;
	@ApiModelProperty(value="创建时间" ,required=false)
	private Date createTime;
	@ApiModelProperty(value="修改时间" ,required=false)
	private Date updateTime;
	@ApiModelProperty(value="停车场支付方式" ,required=false)
	private List<PaymentWayEntity> paymentWayEntitys;
	@ApiModelProperty(value="停车场类型" ,required=false)
	private List<ParkingLotType> parkingLotTypes;
	public List<ParkingLotType> getParkingLotTypes() {
		return parkingLotTypes;
	}
	public void setParkingLotTypes(List<ParkingLotType> parkingLotTypes) {
		this.parkingLotTypes = parkingLotTypes;
	}
	public List<PaymentWayEntity> getPaymentWayEntitys() {
		return paymentWayEntitys;
	}
	public void setPaymentWayEntitys(List<PaymentWayEntity> paymentWayEntitys) {
		this.paymentWayEntitys = paymentWayEntitys;
	}
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParkingLotNumber() {
		return parkingLotNumber;
	}
	public void setParkingLotNumber(String parkingLotNumber) {
		this.parkingLotNumber = parkingLotNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getIsLongrent() {
		return isLongrent;
	}
	public void setIsLongrent(Integer isLongrent) {
		this.isLongrent = isLongrent;
	}
	public Integer getIsChargingPile() {
		return isChargingPile;
	}
	public void setIsChargingPile(Integer isChargingPile) {
		this.isChargingPile = isChargingPile;
	}
	public String[] getImgs() {
		return imgs;
	}
	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public Integer getTotalChargingPileNumber() {
		return totalChargingPileNumber;
	}
	public void setTotalChargingPileNumber(Integer totalChargingPileNumber) {
		this.totalChargingPileNumber = totalChargingPileNumber;
	}
	public Integer getSurplusChargingPileNumber() {
		return surplusChargingPileNumber;
	}
	public void setSurplusChargingPileNumber(Integer surplusChargingPileNumber) {
		this.surplusChargingPileNumber = surplusChargingPileNumber;
	}
	public Integer getTotalParkNumber() {
		return totalParkNumber;
	}
	public void setTotalParkNumber(Integer totalParkNumber) {
		this.totalParkNumber = totalParkNumber;
	}
	public Integer getSurplusParkNumber() {
		return surplusParkNumber;
	}
	public void setSurplusParkNumber(Integer surplusParkNumber) {
		this.surplusParkNumber = surplusParkNumber;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAppointPrice() {
		return appointPrice;
	}
	public void setAppointPrice(BigDecimal appointPrice) {
		this.appointPrice = appointPrice;
	}
	public String getParkingLotTypeId() {
		return parkingLotTypeId;
	}
	public void setParkingLotTypeId(String parkingLotTypeId) {
		this.parkingLotTypeId = parkingLotTypeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	
}
