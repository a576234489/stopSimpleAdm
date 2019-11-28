package com.webside.message.mapper;

import java.util.List;

import com.webside.message.model.AppointmentEntity;
import com.webside.message.model.MessageEntity;
import com.webside.message.model.UserLongRentRecordEntity;
import com.webside.util.PageData;

public interface MessageMapper {
	//在场车辆查询分页
	public List<MessageEntity> findPresentCar(PageData pd);
	//在场车辆结果集查询
	public List<MessageEntity> findPresentCarSubset(List<Integer> ids);
	//出入车辆查询分页
	public List<MessageEntity> findAccessCar(PageData pd);
	//出入车辆结果集查询
	public List<MessageEntity> findAccessCarSubset(List<Integer> ids);
	//订单结果集查询
	public List<MessageEntity> findOrder(PageData pd);
	//停车明细结果集查询收款汇总
	public List<MessageEntity> findStopCarDetail(PageData pd);
	//停车明细结果集查询收款汇总不分页
	public MessageEntity findStopCarDetailAll(PageData pd);
	//预约明细结果集查询收款汇总
	public List<AppointmentEntity> findAppointDetail(PageData pd);
	//预约明细结果集查询收款汇总不分页
	public AppointmentEntity findAppointDetailAll(PageData pd);
	//长租卡办理记录分页
	public List<UserLongRentRecordEntity> findUserLongRentRecord(PageData pd);
	//长租卡办理记录不分页 收费求和
	public UserLongRentRecordEntity findUserLongRentRecordAll(PageData pd);
	
}
