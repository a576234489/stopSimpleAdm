package com.webside.department.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.basecontroller.BaseController;
import com.webside.common.Common;
import com.webside.department.model.DepartmentEntity;
import com.webside.department.service.DepartmentService;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
import com.webside.exception.AjaxException;
import com.webside.exception.SystemException;
import com.webside.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/department/")
public class DepartmentController extends BaseController {

	@Autowired
	private DepartmentService departmentServiec;

	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil();
			if (request.getParameterMap().containsKey("page")) {
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/department/list";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "list.html", method = RequestMethod.POST)
	public Object list(String gridPager, HttpServletResponse response) throws Exception {
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			parameters.put("name", null);
		}
		// 3、判断是否是导出操作
		if (pager.getIsExport()) {
			if (pager.getExportAllData()) {
				// 3.1、导出全部数据
				List<DepartmentEntity> list = departmentServiec.queryListByPage(parameters);
				ExportUtils.exportAll(response, pager, list);
				return null;
			} else {
				// 3.2、导出当前页数据
				ExportUtils.export(response, pager);
				return null;
			}
		} else {
			// 设置分页，page里面包含了分页信息
			Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
			List<DepartmentEntity> list = departmentServiec.queryListByPage(parameters);
			parameters.clear();
			parameters.put("isSuccess", Boolean.TRUE);
			parameters.put("nowPage", pager.getNowPage());
			parameters.put("pageSize", pager.getPageSize());
			parameters.put("pageCount", page.getPages());
			parameters.put("recordCount", page.getTotal());
			parameters.put("startRecord", page.getStartRow());
			// 列表展示数据
			parameters.put("exhibitDatas", list);
			return parameters;
		}
	}
	
	
	@RequestMapping("listGridUI.html")
	public String listTreeUI(Model model, HttpServletRequest request) {
		try
		{
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/department/listGrid";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	
	@RequestMapping("listGrid.html")
	@ResponseBody
	public Object listTree(String gridPager) {
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			parameters.put("name", null);
			parameters.put("parentId", null);
		}else if(!parameters.containsKey("name"))
		{
			parameters.put("name", null);
		}
		Page<Object> page = null;
		if(null == parameters.get("parentId") || "".equals(parameters.get("parentId")))
		{
			// 设置分页，page里面包含了分页信息
			page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "id DESC");
		}
		List<DepartmentEntity> list = departmentServiec.queryTreeGridListByPage(parameters);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		if(null != page)
		{
			parameters.put("nowPage", pager.getNowPage());
			parameters.put("pageSize", pager.getPageSize());
			parameters.put("pageCount", page.getPages());
			parameters.put("recordCount", page.getTotal());
			parameters.put("startRecord", page.getStartRow());
		}
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	

	@RequestMapping("addUI.html")
	public String addUI(Model model, HttpServletRequest request) {
		List<DepartmentEntity> list = departmentServiec.queryListByPage(new HashMap<String, Object>());
		list = subHandle(list, Long.valueOf(0));
		model.addAttribute("list", JSON.toJSON(list));
		return Common.BACKGROUND_PATH + "/department/form";
	}

	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, Long id) {
		try {
			DepartmentEntity department = departmentServiec.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			List<DepartmentEntity> list = departmentServiec.queryListByPage(new HashMap<String, Object>());
			list = subHandle(list, Long.valueOf(0));
			model.addAttribute("list", JSON.toJSON(list));
			model.addAttribute("department", department);
			return Common.BACKGROUND_PATH + "/department/form";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(DepartmentEntity departmentEntity) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			int result = 0;
			if (departmentEntity.getId() == null) {
				departmentEntity.setCreateDate(new Date());
				result = departmentServiec.insert(departmentEntity);
			} else {
				result = departmentServiec.update(departmentEntity);
			}
			if (result == 1) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			} else {
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
		} catch (Exception e) {
			throw new AjaxException(e);
		}
		return map;
	}

	public List<DepartmentEntity> subHandle(List<DepartmentEntity> departments, Long pId) {
		List<DepartmentEntity> subpas = new ArrayList<>();
		for (DepartmentEntity departmentEntity : departments) {
			if (pId.equals(departmentEntity.getParentId())) {
				List<DepartmentEntity> list = departmentEntity.getSubDepas();
				if (list == null) {
					list = new ArrayList<>();
				}
				list = subHandle(departments, departmentEntity.getId());
				departmentEntity.setSubDepas(list);
				subpas.add(departmentEntity);
			}
		}
		return subpas;

	}
	
	@RequestMapping("delete.html")
	@ResponseBody
	public Object delete(Long ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			int result = departmentServiec.deleteById(ids);
			
			if (result == 1) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "删除成功");
			} else {
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "删除失败");
			}
		} catch (Exception e) {
			throw new AjaxException(e);
		}
		return map;
	}

}
