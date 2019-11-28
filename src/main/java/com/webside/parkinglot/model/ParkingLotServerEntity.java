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

@Alias("parkingLotServerEntity")
@ApiModel(value="停车场服务模型")
public class ParkingLotServerEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="id" ,required=false)
	private Integer id;
	@ApiModelProperty(value="停车场id" ,required=false)
	@NotNull(message="停车场id不能为空")
	private Integer parkingLotId;
	@ApiModelProperty(value="服务类型名称" ,required=false)
	@NotEmpty(message="服务类型名称不能为空")
	private String servName;
	@ApiModelProperty(value="服务类型图片地址" ,required=false)
	private String imgUrl;
	@ApiModelProperty(value="服务类型图片" ,required=false)
	@NotEmpty(message="服务类型图片不能为空")
	private String imgs[];
	@ApiModelProperty(value="创建时间" ,required=false)
	private Date createTime;
	@ApiModelProperty(value="修改时间" ,required=false)
	private Date updateTime;
	@ApiModelProperty(value="停车场名称" ,required=false)
	private String parkingLotName;
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
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
	public String getServName() {
		return servName;
	}
	public void setServName(String servName) {
		this.servName = servName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String[] getImgs() {
		return imgs;
	}
	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}
	
	
}
