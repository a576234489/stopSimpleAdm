package com.webside.menu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.webside.menu.model.MenuEntity;
import com.webside.util.PageData;
import com.webside.util.PageResult;

public interface MenuService {
	public PageResult<List<MenuEntity>> findPage(PageData pd);
	public void add(MenuEntity menuEntity);
	public MenuEntity findById(Integer id);
	public int update(MenuEntity menuEntity);
	public void delete(int[] ids);
	public List<MenuEntity> findByNameUrl(MenuEntity menuEntity);
}
