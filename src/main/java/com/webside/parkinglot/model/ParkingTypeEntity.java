package com.webside.parkinglot.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Alias("parkingLotPayment")
@ApiModel(value="停车场和类型中间模型")
public class ParkingTypeEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="停车场id" ,required=false)
	private Integer parkingLotId;
	@ApiModelProperty(value="停车场类型id" ,required=false)
	private Integer parkingLotTypeId;
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
	public Integer getParkingLotTypeId() {
		return parkingLotTypeId;
	}
	public void setParkingLotTypeId(Integer parkingLotTypeId) {
		this.parkingLotTypeId = parkingLotTypeId;
	}
	
	
	
}
