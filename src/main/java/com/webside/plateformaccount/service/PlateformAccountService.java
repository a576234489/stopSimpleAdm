package com.webside.plateformaccount.service;

import java.util.List;

import com.webside.plateformaccount.model.UserEntity;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

public interface PlateformAccountService {
	public PageResult<List<UserEntity>> findPage(PageData pd);
	public void add(UserEntity userEntity);
	public UserEntity findById(Integer id);
	public UserEntity findByName(String name);
	public void update(UserEntity userEntity);
	public void delete(int[] ids);
	public void updateLock(Integer id);
	public void updateUnlock(Integer id);
	public JsonResult updateResetPassword(Integer id,String password);
	public JsonResult findInfo(PageData pd);
	public void updateInfo(UserEntity userEntity);
	public JsonResult updatePass(PageData pd);
}
