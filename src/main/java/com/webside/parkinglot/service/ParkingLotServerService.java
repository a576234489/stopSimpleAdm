package com.webside.parkinglot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.webside.parkinglot.model.ParkingLotEntity;
import com.webside.parkinglot.model.ParkingLotServerEntity;
import com.webside.util.PageData;
import com.webside.util.PageResult;

public interface ParkingLotServerService {
	//分页查询停车场服务
	public PageResult<List<ParkingLotServerEntity>> findPage(PageData pd);
	//更具id查询停车场服务
	public ParkingLotServerEntity findById(Integer id);
	//修改停车场服务
	public void update(ParkingLotServerEntity parkingLotServerEntity,HttpServletRequest request);
	//添加停车场服务
	public void add(ParkingLotServerEntity parkingLotServerEntity,HttpServletRequest request);
	//删除停车场服务
	public void delete(int[] ids);
}
