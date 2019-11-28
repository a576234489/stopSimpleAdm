package com.webside.role.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import jodd.util.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.basecontroller.BaseController;
import com.webside.common.Common;
import com.webside.common.model.JSTreeEntity;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
import com.webside.exception.AjaxException;
import com.webside.exception.SystemException;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.resource.model.ResourceEntity;
import com.webside.resource.service.ResourceService;
import com.webside.role.model.RoleEntity;
import com.webside.role.service.RoleService;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;
import com.webside.util.PageUtil;
import com.webside.util.TreeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/role")
@Api(value = "角色相关的控制器")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	@ResponseBody
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	@ApiOperation(value="查询出除超级管理员外的所有角色")
	public List<RoleEntity> findAll() {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("noContainSuperAdmin", 1);
		return roleService.findAll(pd);
	}
	@ResponseBody
	@RequestMapping(value = "findAllCotainSuperAdm", method = RequestMethod.GET)
	@ApiOperation(value="查询出包含超级管理员外的所有角色")
	public List<RoleEntity> findAllCotainSuperAdm() {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("noContainSuperAdmin", 2);
		return roleService.findAll(pd);
	}
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value="分页查出角色列表数据")
	public PageResult<List<RoleEntity>> list(){
		PageData pd = new PageData();
		pd = this.getPageData();
		return roleService.findPage(pd);
	}
	
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ApiOperation(value="添加角色")
	public Object add(@Valid @RequestBody RoleEntity roleEntity,BindingResult result2)
	{
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		try{
			RoleEntity entity = roleService.findByName(roleEntity.getName());
			if(entity != null) {
				return new JsonResult(false, null, "角色名重复了");
			}
			roleService.add(roleEntity);
			return new JsonResult(true, null, "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "添加失败");
		}
	}
	@RequestMapping(value = "findById",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="更具id查询角色")
	public RoleEntity findById(Integer id) {
		return roleService.findById(id);
	}
	
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="更具id修改角色")
	public Object update(@Valid @RequestBody RoleEntity roleEntity,BindingResult result2)
	{
		List<String> listInfo = new ArrayList<String>();
		if(result2.hasErrors()) {
			List<FieldError> errors=result2.getFieldErrors();
            for (FieldError fieldError : errors) {
                listInfo.add(fieldError.getDefaultMessage());
            }
            return new JsonResult(false, null, StringUtils.join(listInfo, ","));
		}
		RoleEntity entity = roleService.findByName(roleEntity.getName());
		if(entity != null) {
        	if(entity.getId()!=roleEntity.getId()){
        		return new JsonResult(false, null, "该角色名称以存在");
        	}
		}
		try
		{
			roleService.update(roleEntity);
			return new JsonResult(true, null, "修改成功");
		}catch(Exception e)
		{
			e.printStackTrace();
			return new JsonResult(false, null, "修改失败");
		}
	}
	@RequestMapping(value = "del",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="批量删除角色")
	public JsonResult deleteBatch(@RequestParam("ids")int[] ids) {
		if(ids.length <= 0 ) {
			return new JsonResult(false, null, "删除失败,没有选择要删除的行");
		}
		return roleService.delete(ids);
	}
	@RequestMapping(value = "resourceTree",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="获取权限资源树列表")
	public Object resourceTree(@RequestParam("roleId") int roleId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		List<JSTreeEntity> jstreeList = null;
		try{
			parameter.put("roleId", roleId);
			List<ResourceEntity> list = resourceService.queryResourceList(parameter);
			jstreeList = new TreeUtil().generateJSTree(list);
		}
		catch (Exception e) {
			jstreeList = new ArrayList<JSTreeEntity>();
			logger.error(e.getMessage(), e);
		}
		return jstreeList;
	}
	
//	@RequestMapping("permissionUI.html")
//	public String permissionUI(Model model, HttpServletRequest request, Long id) {
//		try
//		{
//			RoleEntity roleEntity = roleService.findById(id);
//			PageUtil page = new PageUtil();
//			page.setPageNum(Integer.valueOf(request.getParameter("page")));
//			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
//			page.setOrderByColumn(request.getParameter("sidx"));
//			page.setOrderByType(request.getParameter("sord"));
//			model.addAttribute("page", page);
//			model.addAttribute("roleEntity", roleEntity);
//			return Common.BACKGROUND_PATH + "/role/permission";
//		}catch(Exception e)
//		{
//			throw new SystemException(e);
//		}
//	}
//	
//	
	@RequestMapping(value = "grantPermission",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="授予角色权限")
	public Object permission(@RequestParam("roleId")int roleId,@RequestParam("resourceIds") String resourceIds)
	{
		try
		{
			List<Integer> list = new ArrayList<Integer>();
			if(StringUtil.isNotBlank(resourceIds))
			{
				for (String id : resourceIds.split(",")) {
					list.add(Integer.valueOf(id));
				}
			}
			boolean result = roleService.addRolePermBatch(roleId, list);
			if(result)
			{
				return new JsonResult(true, null, "授权成功");
			}else
			{
				return new JsonResult(false, null, "授权失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
//	
//	@RequestMapping("withoutAuth/validateRoleName.html")
//	@ResponseBody
//	public Object validateRoleName(@RequestParam(value="name")String roleName){
//		try
//		{
//			roleName = new String(roleName.getBytes("iso-8859-1"),"utf-8");
//			RoleEntity roleEntity = roleService.findByName(roleName);
//			if(roleEntity == null)
//			{
//				return true;
//			}else
//			{
//				return false;
//			}
//		}catch(Exception e)
//		{
//			throw new AjaxException(e);
//		}
//	}
	
}
