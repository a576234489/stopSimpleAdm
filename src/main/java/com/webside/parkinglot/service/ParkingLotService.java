package com.webside.parkinglot.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.webside.parkinglot.model.ParkingLotEntity;
import com.webside.parkinglot.model.ParkingLotType;
import com.webside.parkinglot.model.PaymentWayEntity;
import com.webside.parkinglot.model.SelectFormResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

public interface ParkingLotService {
    public void add (ParkingLotEntity parkingLotEntity,HttpServletRequest request);
    public ParkingLotEntity findByName(String name);
    public PageResult<List<ParkingLotEntity>> findPage(PageData pd);
    public List<ParkingLotEntity> findAll();
    public ParkingLotEntity findById(Integer id);
    public void update(ParkingLotEntity parkingLotEntity,HttpServletRequest request);
    public void delete(int[] ids);
    public List<PaymentWayEntity> findAllPaymentWay();
    public List<ParkingLotType> findAllParkingLotType();
    public List<SelectFormResult>findHavingPaymentWay(Integer id);
    public List<SelectFormResult>findHavingParkingLotType(Integer id);
}
