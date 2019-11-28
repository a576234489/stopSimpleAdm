package com.webside.message.service;

import java.util.List;

import com.webside.message.mapper.MessageMapper;
import com.webside.message.model.AppointmentEntity;
import com.webside.message.model.MessageEntity;
import com.webside.message.model.UserLongRentRecordEntity;
import com.webside.util.PageData;
import com.webside.util.PageResult;

public interface MessageService {
	public PageResult<List<MessageEntity>> findPresentCar(PageData pd);
	public PageResult<List<MessageEntity>> findAccessCar(PageData pd);
	public PageResult<List<MessageEntity>> findOrder(PageData pd);
	public PageResult<List<MessageEntity>>  findStopCarDetail(PageData pd);
	public PageResult<List<AppointmentEntity>>  findAppointDetail(PageData pd);
	public PageResult<List<UserLongRentRecordEntity>> findUserLongRentRecord(PageData pd);
}
