package com.webside.plateformaccount.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webside.base.basecontroller.BaseController;
import com.webside.exception.AjaxException;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.plateformaccount.service.PlateformAccountService;
import com.webside.role.service.RoleService;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("/plateformAccount")
@Api(value = "平台账号相关的控制器")
public class PlateformAccountController extends BaseController{
	@Autowired
	private PlateformAccountService plateformAccountService;
	@Value("${shiro.hashIterations}")
	private String hashIterations;
	// 重置密码与生成账号密码a
	private final String password = "tjj123";
	
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value="分页查出平台账号列表数据")
	public PageResult<List<UserEntity>> list() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		return plateformAccountService.findPage(pd);
	}
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ApiOperation(value="添加平台账号")
	public JsonResult add(@Valid @RequestBody UserEntity userEntity,BindingResult result2) throws AjaxException {
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			UserEntity entity = plateformAccountService.findByName(userEntity.getAccountName());
			if(entity != null) {
				return new JsonResult(false, null, "账号名重复了");
			}
			plateformAccountService.add(userEntity);
			return new JsonResult(true, null, "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "添加失败");
		}
	}
	@RequestMapping(value = "findById",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="更具id查询平台账号")
	public UserEntity findById(Integer id) {
		return plateformAccountService.findById(id);
	}
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="更具id修改平台账号")
	public JsonResult update(@Valid @RequestBody UserEntity userEntity,BindingResult result2){
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			plateformAccountService.update(userEntity);
			return new JsonResult(true, null, "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "修改失败");
		}
	}
	@RequestMapping(value = "del",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="批量删除平台账号")
	public JsonResult del(@RequestParam("ids")int[] ids) {
		if(ids.length <= 0 ) {
			return new JsonResult(false, null, "删除失败,没有选择要删除的行");
		}
		try{
			plateformAccountService.delete(ids);
			return new JsonResult(true, null, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "删除失败");
		}
	}
	@RequestMapping(value = "lock",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="锁定平台账号")
	public JsonResult updateLock(@RequestParam("id") Integer id){
		if(id == null) {
			return new JsonResult(false, null, "参数为空");
		}
		try{
			plateformAccountService.updateLock(id);
			return new JsonResult(true, null, "锁定成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "锁定失败");
		}
	}

	@RequestMapping(value = "unlock",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="解锁平台账号")
	public JsonResult updateUnlock(@RequestParam("id") Integer id){
		if(id == null) {
			return new JsonResult(false, null, "参数为空");
		}
		try{
			plateformAccountService.updateUnlock(id);
			return new JsonResult(true, null, "解锁成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "解锁失败");
		}
	}
	@RequestMapping(value = "resetPassword",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="平台账号重置密码")
	public JsonResult updateResetPassword(@RequestParam("id") Integer id) {
		return plateformAccountService.updateResetPassword(id, password);
	}
	@ResponseBody
	@RequestMapping(value = "findInfo", method = RequestMethod.GET)
	@ApiOperation(value="查看资料")
	public JsonResult findInfo() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		return plateformAccountService.findInfo(pd);
	}
	@RequestMapping(value = "updateInfo",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="修改资料")
	public JsonResult updateInfo(@Valid @RequestBody UserEntity userEntity,BindingResult result2){
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			plateformAccountService.updateInfo(userEntity);
			return new JsonResult(true, null, "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "修改失败");
		}
	}
	@RequestMapping(value = "updatePass",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="修改密码")
	public JsonResult updatePass(){
		PageData pd = new PageData();
		pd = this.getPageData();
		try{	
			return plateformAccountService.updatePass(pd);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "修改失败");
		}
	}
}
