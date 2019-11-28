package com.webside.message.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webside.base.basecontroller.BaseController;
import com.webside.message.model.AppointmentEntity;
import com.webside.message.model.MessageEntity;
import com.webside.message.model.UserLongRentRecordEntity;
import com.webside.message.service.MessageService;
import com.webside.util.PageData;
import com.webside.util.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("/message")
@Api(value = "信息查询的控制器")
public class MessageController extends BaseController{
	
	@Autowired
	private MessageService messageService;
	
	@ResponseBody
	@RequestMapping(value = "findPresentCar", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场中所有在场车辆")
	public PageResult<List<MessageEntity>> findPresentCar(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return messageService.findPresentCar(pd);
	}
	
	@ResponseBody
	@RequestMapping(value = "findAccessCar", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场中所有车辆出入记录")
	public PageResult<List<MessageEntity>> findAccessCar(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return messageService.findAccessCar(pd);
	}
	
	@ResponseBody
	@RequestMapping(value = "findOrder", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场中所有车辆出入记录")
	public PageResult<List<MessageEntity>> findOrder(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return messageService.findOrder(pd);
	}
	@ResponseBody
	@RequestMapping(value = "findStopCarDetail", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场中停车明细记录")
	public PageResult<List<MessageEntity>> findStopCarDetail(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return messageService.findStopCarDetail(pd);
	}
	@ResponseBody
	@RequestMapping(value = "findAppointDetail", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场中停车明细记录")
	public PageResult<List<AppointmentEntity>> findAppointDetail(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return messageService.findAppointDetail(pd);
	}
	@ResponseBody
	@RequestMapping(value = "findUserLongRentRecord", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场中办理的长租卡记录")
	public PageResult<List<UserLongRentRecordEntity>> findUserLongRentRecord(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return messageService.findUserLongRentRecord(pd);
	}
}
