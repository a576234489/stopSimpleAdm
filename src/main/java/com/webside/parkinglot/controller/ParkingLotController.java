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
import com.webside.parkinglot.model.ParkingLotType;
import com.webside.parkinglot.model.PaymentWayEntity;
import com.webside.parkinglot.model.SelectFormResult;
import com.webside.parkinglot.service.ParkingLotService;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/parkingLot")
@Api(value = "停车场相关的控制器")
public class ParkingLotController extends BaseController{
	@Autowired
	private ParkingLotService parkingLotService;
	
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value="分页查出所有停车场列表数据")
	public PageResult<List<ParkingLotEntity>> list(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return parkingLotService.findPage(pd);
	}
	@ResponseBody
	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	@ApiOperation(value="查出所有停车场列表数据")
	public List<ParkingLotEntity> findAll(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return parkingLotService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ApiOperation(value="添加停车场")
	public JsonResult add(@Valid @RequestBody ParkingLotEntity parkingLotEntity,BindingResult result2,HttpServletRequest request){
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			ParkingLotEntity entity = parkingLotService.findByName(parkingLotEntity.getName());
			if(entity != null) {
				return new JsonResult(false, null, "停车场名称重复了");
			}
			parkingLotService.add(parkingLotEntity,request);
			return new JsonResult(true, null, "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "添加失败");
		}
	}
	@RequestMapping(value = "findById",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="更具id查询停车场")
	public ParkingLotEntity findById(Integer id) {
		return parkingLotService.findById(id);
	}
	@RequestMapping(value = "findAllPaymentWay",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="查询所有停车场支付方式")
	public List<PaymentWayEntity> findAllPaymentWay() {
		return parkingLotService.findAllPaymentWay();
	}
	@RequestMapping(value = "findHavingPaymentWay",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="查询停车场已有的支付方式")
	public List<SelectFormResult> findHavingPaymentWay(@RequestParam("id") Integer id) {
		return parkingLotService.findHavingPaymentWay(id);
	}
	@RequestMapping(value = "findAllParkingLotType",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="查询所有停车场类型")
	public List<ParkingLotType> findAllParkingLotType() {
		return parkingLotService.findAllParkingLotType();
	}
	@RequestMapping(value = "findHavingParkingLotType",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="查询已有停车场类型")
	public List<SelectFormResult> findHavingParkingLotType(@RequestParam("id") Integer id) {
		return parkingLotService.findHavingParkingLotType(id);
	}
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="更具id修改停车场")
	public JsonResult update(@Valid @RequestBody ParkingLotEntity parkingLotEntity,BindingResult result2,HttpServletRequest request){
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		ParkingLotEntity entity = parkingLotService.findByName(parkingLotEntity.getName());
		if(entity != null) {
			if(entity.getId() != parkingLotEntity.getId()) {
				return new JsonResult(false, null, "该停车场名称已存在");
			}
		}
		try{
			parkingLotService.update(parkingLotEntity,request);
			return new JsonResult(true, null, "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "修改失败");
		}
	}
	@RequestMapping(value = "del",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="批量删除停车场")
	public JsonResult del(@RequestParam("ids")int[] ids) {
		if(ids.length <= 0 ) {
			return new JsonResult(false, null, "删除失败,没有选择要删除的行");
		}
		try{
			parkingLotService.delete(ids);
			return new JsonResult(true, null, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "删除失败");
		}
	}
}
