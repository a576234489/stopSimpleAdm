package com.webside.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.webside.menu.service.MenuService;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/menu")
@Api(value = "菜单相关的控制器")
public class MenuController extends BaseController{
	@Autowired
	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value="查出所有菜单列表数据")
	public PageResult<List<MenuEntity>> list(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return menuService.findPage(pd);
	}
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ApiOperation(value="添加菜单")
	public JsonResult add(@Valid @RequestBody MenuEntity menuEntity,BindingResult result2) {
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try
		{
			List<MenuEntity> list = menuService.findByNameUrl(menuEntity);
			if(list.size()>0) {
				return new JsonResult(false, null, "名称或者资源路径重复了");
			}
			menuService.add(menuEntity);
			return new JsonResult(true, null, "添加成功");
		}catch(Exception e)
		{
			e.printStackTrace();
			return new JsonResult(false, null, "添加失败");
		}
	}
	@RequestMapping(value = "findById",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="更具id菜单信息")
	public MenuEntity findById(Integer id) {
		return menuService.findById(id);
	}
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="更具id修改平台账号")
	public JsonResult update(@Valid @RequestBody MenuEntity menuEntity,BindingResult result2){
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			List<MenuEntity> list = menuService.findByNameUrl(menuEntity);
			if(list.size()>0) {
				if(list.get(0).getId() != menuEntity.getId()) {
					return new JsonResult(false, null, "名称或者资源路径重复了");
				}
			}
			menuService.update(menuEntity);
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
			menuService.delete(ids);
			return new JsonResult(true, null, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "删除失败");
		}
	}
}
