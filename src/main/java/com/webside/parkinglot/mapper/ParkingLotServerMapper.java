package com.webside.parkinglot.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.webside.parkinglot.model.ParkingLotServerEntity;
import com.webside.util.PageData;


@Repository
public interface ParkingLotServerMapper {
	//分页查询停车场服务
	public List<ParkingLotServerEntity> findPage(PageData pd);
	//更具id查询停车场服务
	public ParkingLotServerEntity findById(@Param("id") Integer id);
	//修改停车场服务
	public void update(ParkingLotServerEntity parkingLotServerEntity);
	//添加停车场服务
	public void insert(ParkingLotServerEntity parkingLotServerEntity);
	//删除停车场服务
	public void delete(int[] ids);
}
