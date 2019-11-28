package com.webside.parkinglot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webside.base.basecontroller.BaseController;
import com.webside.menu.model.MenuEntity;
import com.webside.parkinglot.model.ParkingLotEntity;
import com.webside.parkinglot.model.ParkingLotServerEntity;
import com.webside.parkinglot.service.ParkingLotServerService;
import com.webside.parkinglot.service.ParkingLotService;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/parkingLotServer")
@Api(value = "停车场服务相关的控制器")
public class ParkingLotServerController extends BaseController{
	@Autowired
	private ParkingLotServerService parkingLotServerService;
	
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场服务列表数据")
	public PageResult<List<ParkingLotServerEntity>>list(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return parkingLotServerService.findPage(pd);
	}
	
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ApiOperation(value="添加停车场服务")
	public JsonResult add(@Valid @RequestBody ParkingLotServerEntity parkingLotServerEntity,BindingResult result2,HttpServletRequest request){
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			parkingLotServerService.add(parkingLotServerEntity,request);
			return new JsonResult(true, null, "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "添加失败");
		}
	}
	@RequestMapping(value = "findById",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="更具id查询停车场服务")
	public ParkingLotServerEntity findById(Integer id) {
		return parkingLotServerService.findById(id);
	}
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="更具id修改停车场服务")
	public JsonResult update(@Valid @RequestBody ParkingLotServerEntity parkingLotServerEntity,BindingResult result2,HttpServletRequest request){
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			parkingLotServerService.update(parkingLotServerEntity,request);
			return new JsonResult(true, null, "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "修改失败");
		}
	}
	@RequestMapping(value = "del",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="批量删除停车场服务")
	public JsonResult del(@RequestParam("ids")int[] ids) {
		if(ids.length <= 0 ) {
			return new JsonResult(false, null, "删除失败,没有选择要删除的行");
		}
		try{
			parkingLotServerService.delete(ids);
			return new JsonResult(true, null, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "删除失败");
		}
	}
}
