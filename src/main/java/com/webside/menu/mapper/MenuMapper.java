package com.webside.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.webside.menu.model.MenuEntity;
import com.webside.util.PageData;

public interface MenuMapper {
	//查出所有菜单
	public List<MenuEntity> findPage(PageData pd);
	//添加菜单
	public int insert(MenuEntity menuEntity);
	//根据id查询菜单
	public MenuEntity findById(@Param("id") Integer id);
	//修改菜单
	public int update(MenuEntity menuEntity);
	//删除菜单表
	public int deleteResources(int[] ids);
	//删除菜单角色表
	public int deleteResourcesRole(int[] ids);
	//更具名称和资源路径查询菜单
	public List<MenuEntity> findByNameUrl(MenuEntity menuEntity);
}
