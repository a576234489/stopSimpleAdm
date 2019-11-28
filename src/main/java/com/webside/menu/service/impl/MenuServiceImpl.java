package com.webside.menu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.menu.mapper.MenuMapper;
import com.webside.menu.model.MenuEntity;
import com.webside.menu.service.MenuService;
import com.webside.role.mapper.RoleMapper;
import com.webside.role.model.RoleEntity;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.PageData;
import com.webside.util.PageResult;

@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public PageResult<List<MenuEntity>> findPage(PageData pd) {
		
		 List<MenuEntity> menuEntity = menuMapper.findPage(pd);
		 PageResult<List<MenuEntity>> pageResult = new PageResult<List<MenuEntity>>("", menuEntity, 0, menuEntity.size());
		 return pageResult;
	}

	@Override
	public void add(MenuEntity menuEntity) {
		menuEntity.setCreateTime(new Date());
		menuEntity.setUpdateTime(new Date());
		menuEntity.setSourceKey(menuEntity.getSourceUrl());
		menuEntity.setIsOpen(false);
		menuMapper.insert(menuEntity);
		//2、超级管理员直接赋予该权限
		RoleEntity role = roleMapper.findByName("超级管理员");
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("roleId", role.getId());
		parameter.put("resourceId", menuEntity.getId());
		roleMapper.addRoleResource(parameter);
		//清空所有用户权限,重新加载权限
		ShiroAuthenticationManager.clearAllUserAuth();
	}

	@Override
	public MenuEntity findById(Integer id) {
		return menuMapper.findById(id);
	}

	@Override
	public int update(MenuEntity menuEntity) {
		menuEntity.setUpdateTime(new Date());
		menuEntity.setSourceKey(menuEntity.getSourceUrl());
		return menuMapper.update(menuEntity);
	}

	@Override
	public void delete(int[] ids) {
		menuMapper.deleteResourcesRole(ids);
		menuMapper.deleteResources(ids);
		ShiroAuthenticationManager.clearAllUserAuth();
	}

	@Override
	public List<MenuEntity> findByNameUrl(MenuEntity menuEntity) {
		return menuMapper.findByNameUrl(menuEntity);
	}

}
