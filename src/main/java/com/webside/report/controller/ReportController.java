package com.webside.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webside.base.basecontroller.BaseController;
import com.webside.report.model.ReportEntity;
import com.webside.report.service.ReportService;
import com.webside.util.PageData;
import com.webside.util.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/report")
@Api(value = "报表查询的控制器")
public class ReportController extends BaseController{
	@Autowired
	private ReportService reportService;
	
	@ResponseBody
	@RequestMapping(value = "findReport", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场中所有在场车辆")
	public PageResult<List<ReportEntity>> findPresentCar(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return reportService.dayRport(pd);
	}
}
