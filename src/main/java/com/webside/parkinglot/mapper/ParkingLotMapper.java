package com.webside.parkinglot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.webside.parkinglot.model.ParkingLotEntity;
import com.webside.parkinglot.model.ParkingLotPayment;
import com.webside.parkinglot.model.ParkingLotType;
import com.webside.parkinglot.model.ParkingTypeEntity;
import com.webside.parkinglot.model.PaymentWayEntity;
import com.webside.util.PageData;

@Repository
public interface ParkingLotMapper {
	//添加停车场
    public void insert (ParkingLotEntity parkingLotEntity);
    //添加停车场和支付类型中间表
    public void insertParkingLotPayment(ParkingLotPayment parkingLotPaymentEntity);
    //添加停车场和停车场类型中间表
    public void insertParkingType(ParkingTypeEntity parkingTypeEntity);
    //更具名字查询停车场
    public ParkingLotEntity findByName(@Param("name")String name);
    //更具id查询停车场
    public ParkingLotEntity findById(@Param("id")Integer id);
    //分页查询停车场
    public List<ParkingLotEntity> findPage(PageData pd);
    //查询停车场子集
    public List<ParkingLotEntity> findPageSubset(PageData pd);
    //更具停车场id删除停车场停车场支付中间表
    public void deleteParkingLotPayment(@Param("id")Integer id);
    //更具停车场id删除停车场与停车场类型中间表
    public void deleteParkingType(@Param("id")Integer id);
    //修改停车场
    public void update(ParkingLotEntity parkingLotEntity);
    //批量删除停车场支付中间表
    public void deleteParkingLotPaymentBatch(int[] ids);
    //批量删除停车场类型中间表
    public void deleteParkingTypeBatch(int[] ids);
    //批量删除停车场
    public void deleteParking(int[] ids);
    //查询所有停车场支付方式
    public List<PaymentWayEntity> findAllPaymentWay();
    //查询所有停车场类型
    public List<ParkingLotType> findAllParkingLotType();
  
    
}
