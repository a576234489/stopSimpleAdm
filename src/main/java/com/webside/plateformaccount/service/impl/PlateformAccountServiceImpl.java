package com.webside.plateformaccount.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.plateformaccount.mapper.PlateformAccountMapper;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.plateformaccount.model.UserInfoEntity;
import com.webside.plateformaccount.service.PlateformAccountService;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;
import com.webside.util.crypto.EndecryptUtils;
@Service
public class PlateformAccountServiceImpl implements PlateformAccountService{
	@Autowired
	private PlateformAccountMapper plateformAccountMapper;
	@Override
	public PageResult<List<UserEntity>> findPage(PageData pd) {
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()), Integer.parseInt(pd.get("limit").toString()));
		List<UserEntity> list = plateformAccountMapper.findPage(pd);
		return new PageResult<List<UserEntity>>("", list, 0, (int) page.getTotal());
	}
	@Override
	public void add(UserEntity userEntity) {
		// 加密用户输入的密码，得到密码和加密盐，保存到数据库
		UserEntity user = EndecryptUtils.md5Password(userEntity.getAccountName(), userEntity.getPassword(), 2);
		// 设置添加用户的密码和加密盐
		userEntity.setPassword(user.getPassword());
		userEntity.setCredentialsSalt(user.getCredentialsSalt());
		// 设置创建者姓名
		userEntity.setCreatorName(ShiroAuthenticationManager.getUserAccountName());
		userEntity.setCreateTime(new Date(System.currentTimeMillis()));
		// 设置锁定状态：未锁定；删除状态：未删除
		userEntity.setLocked(0);
		userEntity.setDeleteStatus(0);
		UserInfoEntity userInfo = new UserInfoEntity();
		userInfo.setEmail(userEntity.getAccountName());
		userInfo.setCreateTime(new Date());
		userEntity.setUserInfo(userInfo);
		plateformAccountMapper.insert(userEntity);
		plateformAccountMapper.insertUserRole(userEntity);
		userEntity.getUserInfo().setId(userEntity.getId());
		plateformAccountMapper.insertUserInfo(userEntity);
	}
	@Override
	public UserEntity findById(Integer id) {
		return plateformAccountMapper.findById(id);
	}
	@Override
	public void update(UserEntity userEntity) {
		userEntity.setCreatorName(ShiroAuthenticationManager.getUserAccountName());
		plateformAccountMapper.update(userEntity);
		plateformAccountMapper.updateUserRole(userEntity);
		ShiroAuthenticationManager.clearUserAuthByUserId(Long.parseLong(userEntity.getId()+""));
	}
	@Override
	public void delete(int[] ids) {
		plateformAccountMapper.deleteBatchUserRole(ids);
		plateformAccountMapper.deleteBatchById(ids);
		plateformAccountMapper.deleteBatchUserInfo(ids);
	}
	@Override
	public UserEntity findByName(String name) {
		return plateformAccountMapper.findByName(name);
	}
	@Override
	public void updateLock(Integer id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		userEntity.setLocked(1);
		plateformAccountMapper.update(userEntity);
	}
	@Override
	public void updateUnlock(Integer id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		userEntity.setLocked(0);
		plateformAccountMapper.update(userEntity);
	}
	@Override
	public JsonResult updateResetPassword(Integer id,String password) {
		UserEntity entity = plateformAccountMapper.findById(id);
		if(entity == null) {
			return new JsonResult(false, null, "要重置密码得账户不存在");
		}
		// 加密用户输入的密码，得到密码和加密盐，保存到数据库
		UserEntity user = EndecryptUtils.md5Password(entity.getAccountName(), password, 2);
		// 设置添加用户的密码和加密盐
		entity.setPassword(user.getPassword());
		entity.setCredentialsSalt(user.getCredentialsSalt());
		try{
			plateformAccountMapper.update(entity);
			return new JsonResult(true, null, "重置密码成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "重置密码失败");
		}
	}
	@Override
	public JsonResult findInfo(PageData pd) {
		UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		UserEntity entity = plateformAccountMapper.findById(userEntity.getId());
		return new JsonResult(entity);
	}
	@Override
	public void updateInfo(UserEntity userEntity) {
		UserInfoEntity userInfo = userEntity.getUserInfo();
		UserEntity userEntitySession = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		userEntity.setId(userEntitySession.getId());
		userEntity.setUpdateTime(new Date());
		userInfo.setId(userEntitySession.getId());
		plateformAccountMapper.update(userEntity);
		plateformAccountMapper.updateUserInfo(userInfo);
	}
	@Override
	public JsonResult updatePass(PageData pd) {
		UserEntity userEntitySession = (UserEntity)SecurityUtils.getSubject().getPrincipal();
		UserEntity entity = plateformAccountMapper.findById(userEntitySession.getId());
		UserEntity user = EndecryptUtils.md5PasswordCompare(entity.getAccountName(), pd.getString("oldPassword"), entity.getAccountName()+entity.getCredentialsSalt(), 2);
		pd.put("oldPassWord", user.getPassword());
		UserEntity userEntity = plateformAccountMapper.findByPass(pd);
		if(userEntity != null) {
			UserEntity user2 = EndecryptUtils.md5Password(entity.getAccountName(), pd.getString("password"), 2);
			user2.setId(userEntitySession.getId());
			plateformAccountMapper.update(user2);
			return new JsonResult(true, "/login.html", "修改密码成功");
		}else {
			return new JsonResult(false, null, "原密码不正确");
		}
	}

}
