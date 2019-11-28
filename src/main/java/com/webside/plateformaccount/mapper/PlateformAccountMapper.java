package com.webside.plateformaccount.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.webside.plateformaccount.model.UserEntity;
import com.webside.plateformaccount.model.UserInfoEntity;
import com.webside.util.PageData;

public interface PlateformAccountMapper {
	//分页更具条件查询账号
	public List<UserEntity> findPage(PageData pd);
	//更具账户名查询平台账号信息
	public UserEntity findByName(@Param("name")String name);
	//更具账户id查询平台账号信息
	public UserEntity findById(@Param("id")Integer id);
	//查找非超级管理员的所有账号
	public List<UserEntity> findAll();
	//账号添加
	public int insert(UserEntity userEntity);
	//账号角色中间表添加
	public int insertUserRole(UserEntity userEntity);
	//账号修改
	public int update(UserEntity userEntity);
	//账号角色中间表修改
	public int updateUserRole(UserEntity userEntity);
	//批量删除账号
	public int deleteBatchById(int[] ids);
	//批量删除账号角色中间表
	public int deleteBatchUserRole(int[] ids);
	//批量删除账号详情表
	public int deleteBatchUserInfo(int[] ids);
	//添加账号个人资料信息
	public int insertUserInfo(UserEntity userEntity);
	//更新用户个人资料信息
	public int updateUserInfo(UserInfoEntity userInfoEntity);
	//查看密码是否重复
	public UserEntity findByPass(PageData pd);
	
	
	
	
	
	
}
