package com.webside.report.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.message.mapper.MessageMapper;
import com.webside.message.model.MessageEntity;
import com.webside.report.mapper.ReportMapper;
import com.webside.report.model.ReportEntity;
import com.webside.report.service.ReportService;
import com.webside.util.PageData;
import com.webside.util.PageResult;
@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ReportMapper reportMapper;
	@Override
	public PageResult<List<ReportEntity>> dayRport(PageData pd) {
		int dayType = Integer.parseInt(pd.get("dayType").toString());
		String day = "";
		String dayAnd = "";
		if(dayType == 1) {
			if(pd.get("openTime") == null) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				day = getSpecifiedDayBefore(sdf.format(d));
			}else {
				day = pd.get("openTime").toString();
			}
			pd.put("openTime", day + " 00:00:00");
			pd.put("endTime", day + " 23:59:59");
		}else if(dayType == 2){
			if(pd.get("openTime") == null) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				day = sdf.format(d);
				dayAnd = getMonthDay(day);
			}else {
				day = pd.get("openTime").toString();
				dayAnd = getMonthDay(day);
			}
			String[] dayArray = dayAnd.split("&");
			pd.put("openTime", dayArray[0] + " 00:00:00");
			pd.put("endTime", dayArray[1] + " 23:59:59");
		}
		int onlyCardNumber = 0;
		ReportEntity StopCarAll = findReort(pd);
		
		List<ReportEntity> listCard = reportMapper.findHandleCard(pd);//查出当天所有停车场的办卡记录
		List<ReportEntity> listStopCarAll = reportMapper.findReport(pd);//查出当天所有停车场的临停记录
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()),
				Integer.parseInt(pd.get("limit").toString()));
		List<ReportEntity> listStopCar = reportMapper.findReport(pd);//分页查出当天所有停车场的临停记录
		for (ReportEntity Card : listCard) {
			boolean isComplete = false;
			boolean isCompleteAll = false;
			for (ReportEntity StopCar : listStopCar) {
				if(StopCar.getId() == Card.getId()) {
					StopCar.setCardQantity(Card.getCardQantity());
					StopCar.setTatolCardMoney(Card.getTatolCardMoney());
					isComplete = true;
				}
			}
			for (ReportEntity StopCar : listStopCarAll) {
				if(StopCar.getId() == Card.getId()) {
					isCompleteAll = true;
				}
			}
			if(!isCompleteAll) {
				onlyCardNumber ++ ;
			}
			if(!isComplete) {
				if(listStopCarAll.size() < 10) {
					listStopCar.add(Card);
				}
			}
		}
		for (ReportEntity StopCar : listStopCar) {
			StopCar.setTotalReceivables(StopCar.getTotalAmount()+StopCar.getTotalDeductionAmount()+StopCar.getTotalBusinessAmount()+StopCar.getTatolCardMoney());
			StopCar.setTotalfavourable(StopCar.getTotalDeductionAmount()+StopCar.getTotalBusinessAmount());
			StopCar.setTotalAmount(StopCar.getTotalAmount()+StopCar.getTatolCardMoney());
			StopCar.setTotalUnCashAmount(StopCar.getTotalAmount() - StopCar.getTotalCashAmount()-StopCar.getTatolCardMoney());
			StopCar.setTime(day);
		}
		listStopCar.add(StopCarAll);
		return new PageResult<List<ReportEntity>>("", listStopCar, 0, (int)page.getTotal()+onlyCardNumber);
	}
	
	public ReportEntity findReort(PageData pd){
		ReportEntity StopCarAll = reportMapper.findReportAll(pd);//查出当天所有停车场的临停记录汇总
		ReportEntity CardAll = reportMapper.findHandleCardAll(pd);
		if(CardAll != null) {
			if(StopCarAll != null){
				StopCarAll.setTotalReceivablesAll(StopCarAll.getTotalAmountAll()+StopCarAll.getTotalDeductionAmountAll()+StopCarAll.getTotalBusinessAmountAll()+CardAll.getTatolCardMoneyAll());
				StopCarAll.setTotalAmountAll(StopCarAll.getTotalAmountAll()+CardAll.getTatolCardMoneyAll());
				StopCarAll.setTotalUnCashAmountAll(StopCarAll.getTotalAmountAll() - StopCarAll.getTotalCashAmountAll()-CardAll.getTatolCardMoneyAll());
				StopCarAll.setCardQantityAll(CardAll.getCardQantityAll());
				StopCarAll.setTatolCardMoneyAll(CardAll.getTatolCardMoneyAll());
				StopCarAll.setTotalfavourableAll(StopCarAll.getTotalDeductionAmountAll()+StopCarAll.getTotalBusinessAmountAll());
			}else {
				StopCarAll = new ReportEntity();
				StopCarAll.setTotalReceivablesAll(CardAll.getTatolCardMoneyAll());
				StopCarAll.setTotalAmountAll(CardAll.getTatolCardMoneyAll());
				StopCarAll.setTotalUnCashAmountAll(CardAll.getTatolCardMoneyAll());
				StopCarAll.setCardQantityAll(CardAll.getCardQantityAll());
				StopCarAll.setTatolCardMoneyAll(CardAll.getTatolCardMoneyAll());
				StopCarAll.setTotalfavourableAll(0);
			}
		}else {
			if(StopCarAll != null){
				StopCarAll.setTotalReceivablesAll(StopCarAll.getTotalAmountAll()+StopCarAll.getTotalDeductionAmountAll()+StopCarAll.getTotalBusinessAmountAll());
				StopCarAll.setTotalAmountAll(StopCarAll.getTotalAmountAll());
				StopCarAll.setTotalUnCashAmountAll(StopCarAll.getTotalAmountAll() - StopCarAll.getTotalCashAmountAll());
				StopCarAll.setCardQantityAll(0);
				StopCarAll.setTatolCardMoneyAll(0);
				StopCarAll.setTotalfavourableAll(StopCarAll.getTotalDeductionAmountAll()+StopCarAll.getTotalBusinessAmountAll());
			}else {
				StopCarAll = new ReportEntity();
			}
		}
		return StopCarAll;
	}
	 
	// 获得指定日期的前一天 
	public static String getSpecifiedDayBefore(String specifiedDay){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day-1); 
	
		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayBefore; 
	} 
	// 获取当月第一天和最后一天  
	public static String getMonthDay(String day) {
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
       SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
       Date date = null;
		try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
       String firstday, lastday;  
       // 获取前月的第一天  
       Calendar cale = Calendar.getInstance();
       cale.setTime(date);
       cale.add(Calendar.MONTH, 0);  
       cale.set(Calendar.DAY_OF_MONTH, 1);  
       firstday = format2.format(cale.getTime());  
       // 获取前月的最后一天  
       cale.add(Calendar.MONTH, 1);  
       cale.set(Calendar.DAY_OF_MONTH, 0);  
       lastday = format2.format(cale.getTime());  
       return firstday+"&"+lastday;
	}

}
