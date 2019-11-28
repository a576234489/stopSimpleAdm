package com.webside.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.message.mapper.MessageMapper;
import com.webside.message.model.AppointmentEntity;
import com.webside.message.model.MessageEntity;
import com.webside.message.model.UserLongRentRecordEntity;
import com.webside.message.service.MessageService;
import com.webside.parkinglot.model.ParkingLotServerEntity;
import com.webside.parkinglot.model.ParkingLotType;
import com.webside.util.PageData;
import com.webside.util.PageResult;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public PageResult<List<MessageEntity>> findPresentCar(PageData pd) {
		// List<MessageEntity> subList = messageMapper.findPresentCarSubset(pd);// 先查子集
		// 比分页记录多
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()),
				Integer.parseInt(pd.get("limit").toString()));
		if (pd.get("underGroundCarpark") != null) {
			if (Integer.parseInt(pd.get("underGroundCarpark").toString()) == 1
					|| Integer.parseInt(pd.get("threeDimensionalCarpark").toString()) == 1
					|| Integer.parseInt(pd.get("groundCarpark").toString()) == 1
					|| Integer.parseInt(pd.get("roadSurveyCarpark").toString()) == 1) {
				pd.put("ParkTypeLeftParentheses", 1);
				pd.put("ParkTypeRightParentheses", 1);
			}
			if (Integer.parseInt(pd.get("longRent").toString()) == 1
					|| Integer.parseInt(pd.get("temporaryCard").toString()) == 1) {
				pd.put("CarTypeLeftParentheses", 1);
				pd.put("CarTypeRightParentheses", 1);
			}
			System.out.println(pd.get("endTime"));
			if(pd.get("endTime") == null || "".equals(pd.get("endTime").toString())) {
				pd.put("endTime", new Date());
			}
		}
		
		List<MessageEntity> list = messageMapper.findPresentCar(pd);
		List<Integer> ids = new ArrayList<Integer>();
		List<MessageEntity> subList = null;
		if (list.size() > 0) {
			for (MessageEntity messageEntity : list) {
				ids.add(messageEntity.getId());
			}
			subList = messageMapper.findPresentCarSubset(ids);
			for (MessageEntity messageEntity : subList) {
				Date admissionTime = messageEntity.getAdmissionTime();
				Date nowDate = new Date();
				long diff = nowDate.getTime() - admissionTime.getTime();// 这样得到的差值是微秒级别
				long days = diff / (1000 * 60 * 60 * 24);
				long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); // 获取时
				long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60); // 获取分钟
				messageEntity.setStopDuration(days + "," + hours + "," + minutes);
			}
		}
		return new PageResult<List<MessageEntity>>("", subList, 0, (int) page.getTotal());
	}

	@Override
	public PageResult<List<MessageEntity>> findAccessCar(PageData pd) {
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()),
				Integer.parseInt(pd.get("limit").toString()));
		if (pd.get("admission") != null) {
			if (Integer.parseInt(pd.get("admission").toString()) == 1
					|| Integer.parseInt(pd.get("exit").toString()) == 1) {
				pd.put("AccessTypeLeftParentheses", 1);
				pd.put("AccessTypeRightParentheses", 1);
			}
			if (Integer.parseInt(pd.get("longRent").toString()) == 1
					|| Integer.parseInt(pd.get("temporaryCard").toString()) == 1) {
				pd.put("CarTypeLeftParentheses", 1);
				pd.put("CarTypeRightParentheses", 1);
			}
			if(pd.get("endTime") == null || "".equals(pd.get("endTime").toString())) {
				pd.put("endTime", new Date());
			}
		}
		
		List<MessageEntity> list = messageMapper.findAccessCar(pd);
		List<Integer> ids = new ArrayList<Integer>();
		List<MessageEntity> subList = null;
		if (list.size() > 0) {
			for (MessageEntity messageEntity : list) {
				ids.add(messageEntity.getId());
			}
			subList = messageMapper.findAccessCarSubset(ids);
			for (MessageEntity messageEntity : subList) {
				Date admissionTime = messageEntity.getAdmissionTime();
				Date nowDate = null;
				if(messageEntity.getExitTime()!=null) {
					nowDate = messageEntity.getExitTime();
				}else {
					nowDate = new Date();
				}
				long diff = nowDate.getTime() - admissionTime.getTime();// 这样得到的差值是微秒级别
				long days = diff / (1000 * 60 * 60 * 24);
				long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); // 获取时
				long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60); // 获取分钟
				messageEntity.setStopDuration(days + "," + hours + "," + minutes);
			}
		}
		return new PageResult<List<MessageEntity>>("", subList, 0, (int) page.getTotal());
	}

	@Override
	public PageResult<List<MessageEntity>> findOrder(PageData pd) {
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()),
				Integer.parseInt(pd.get("limit").toString()));
		if (pd.get("admission") != null) {
			if (Integer.parseInt(pd.get("admission").toString()) == 1
					|| Integer.parseInt(pd.get("exit").toString()) == 1) {
				pd.put("AccessTypeLeftParentheses", 1);
				pd.put("AccessTypeRightParentheses", 1);
			}
			if(pd.get("endTime") == null || "".equals(pd.get("endTime").toString())) {
				pd.put("endTime", new Date());
			}
		}
		List<MessageEntity> list = messageMapper.findOrder(pd);
		for (MessageEntity messageEntity : list) {
			Date nowDate = null;
			if(messageEntity.getExitTime() != null) {
				messageEntity.setTotalfavourable(messageEntity.getBusinessAmount()+messageEntity.getDeductionAmount());
				messageEntity.setTotalReceivables(messageEntity.getBusinessAmount()+messageEntity.getDeductionAmount()+messageEntity.getAmount());
				nowDate = messageEntity.getExitTime();
			}else {
				nowDate = new Date();
			}
			long diff = nowDate.getTime() - messageEntity.getAdmissionTime().getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); // 获取时
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60); // 获取分钟
			messageEntity.setStopDuration(days + "," + hours + "," + minutes);
		}
		return new PageResult<List<MessageEntity>>("", list, 0, (int) page.getTotal());
	}

	@Override
	public PageResult<List<MessageEntity>> findStopCarDetail(PageData pd) {
		if (pd.get("weChat") != null) {
			if (Integer.parseInt(pd.get("weChat").toString()) == 1
					|| Integer.parseInt(pd.get("cash").toString()) == 1
							|| Integer.parseInt(pd.get("alipay").toString()) == 1
					|| Integer.parseInt(pd.get("unionPay").toString()) == 1) {
				pd.put("payWayLeftParentheses", 1);
				pd.put("payWayRightParentheses", 1);
			}
			if(pd.get("endTime") == null || "".equals(pd.get("endTime").toString())) {
				pd.put("endTime", new Date());
			}
		}
		MessageEntity entity = messageMapper.findStopCarDetailAll(pd);
		if(entity != null) {
			entity.setTotalReceivables(entity.getTotalBusinessAmount()+entity.getTotalDeductionAmount()+entity.getTotalAmount());
			entity.setTotalfavourable(entity.getTotalBusinessAmount()+entity.getTotalDeductionAmount());
			Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()),
					Integer.parseInt(pd.get("limit").toString()));
			List<MessageEntity> list = messageMapper.findStopCarDetail(pd);
			for (MessageEntity messageEntity : list) {
				Date nowDate = null;
				if(messageEntity.getExitTime() != null) {
					messageEntity.setTotalfavourable(messageEntity.getBusinessAmount()+messageEntity.getDeductionAmount());
					messageEntity.setTotalReceivables(messageEntity.getBusinessAmount()+messageEntity.getDeductionAmount()+messageEntity.getAmount());
					nowDate = messageEntity.getExitTime();
				}else {
					nowDate = new Date();
				}
				long diff = nowDate.getTime() - messageEntity.getAdmissionTime().getTime();// 这样得到的差值是微秒级别
				long days = diff / (1000 * 60 * 60 * 24);
				long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); // 获取时
				long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60); // 获取分钟
				messageEntity.setStopDuration(days + "," + hours + "," + minutes);
			}
			list.add(entity);
			return  new PageResult<List<MessageEntity>>("", list, 0, (int) page.getTotal());
		}else {
			return  new PageResult<List<MessageEntity>>("没有查到相关数据", null, 0, 0);
		}
	}

	@Override
	public PageResult<List<AppointmentEntity>> findAppointDetail(PageData pd) {
		if (pd.get("appointing") != null) {
			if (Integer.parseInt(pd.get("appointing").toString()) == 1
					|| Integer.parseInt(pd.get("cancelAppoint").toString()) == 1
					|| Integer.parseInt(pd.get("extensionAppointing").toString()) == 1
					|| Integer.parseInt(pd.get("extensionAppointFinish").toString()) == 1
					|| Integer.parseInt(pd.get("appointFinish").toString()) == 1) {
				pd.put("appointStatusLeftParentheses", 1);
				pd.put("appointStatusRightParentheses", 1);
			}
			if(pd.get("endTime") == null || "".equals(pd.get("endTime").toString())) {
				pd.put("endTime", new Date());
			}
		}
		AppointmentEntity entity = messageMapper.findAppointDetailAll(pd);
		if(entity != null) {
			Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()),
			Integer.parseInt(pd.get("limit").toString()));
			List<AppointmentEntity> list = messageMapper.findAppointDetail(pd);
			list.add(entity);
			return  new PageResult<List<AppointmentEntity>>("", list, 0, (int) page.getTotal());
		}else {
			return  new PageResult<List<AppointmentEntity>>("没有查到相关数据", null, 0, 0);
		}
	}

	@Override
	public PageResult<List<UserLongRentRecordEntity>> findUserLongRentRecord(PageData pd) {
		if (pd.get("monthCard") != null) {
			if (Integer.parseInt(pd.get("monthCard").toString()) == 1
					|| Integer.parseInt(pd.get("seasonCard").toString()) == 1
					|| Integer.parseInt(pd.get("yearCard").toString()) == 1) {
				pd.put("longRentTypeLeftParentheses", 1);
				pd.put("longRentTypeRightParentheses", 1);
			}
			if(pd.get("endTime") == null || "".equals(pd.get("endTime").toString())) {
				pd.put("endTime", new Date());
			}
			if (Integer.parseInt(pd.get("cardExpired").toString()) == 1
					|| Integer.parseInt(pd.get("cardUnExpired").toString()) == 1) {
				pd.put("isExpiredLeftParentheses", 1);
				pd.put("isExpiredRightParentheses", 1);
			}
		}
		UserLongRentRecordEntity entity = messageMapper.findUserLongRentRecordAll(pd);
		if(entity != null) {
			Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()),
			Integer.parseInt(pd.get("limit").toString()));
			List<UserLongRentRecordEntity> list = messageMapper.findUserLongRentRecord(pd);
			list.add(entity);
			return  new PageResult<List<UserLongRentRecordEntity>>("", list, 0, (int) page.getTotal());
		}else {
			return  new PageResult<List<UserLongRentRecordEntity>>("没有查到相关数据", null, 0, 0);
		}
	}

}
