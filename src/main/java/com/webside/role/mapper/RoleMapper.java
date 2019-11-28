package com.webside.role.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.webside.base.basemapper.BaseMapper;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.role.model.RoleEntity;
import com.webside.util.PageData;

@Repository
public interface RoleMapper{
	//查询出出超级管理员外得所有角色
	public List<RoleEntity> findAll(PageData pd);
	//分页更具条件查询账号
	public List<RoleEntity> findPage(PageData pd);
	//更具角色名查询角色实体
	public RoleEntity findByName(@Param("name")String name);
	//添加角色
	public int insert (RoleEntity roleEntity);
	//更具id查询角色实体
	public RoleEntity findById(@Param("id") Integer id);
	//修改角色
	public int update(RoleEntity roleEntity);
	//批量删除角色
	public int deleteBatchById(int[] ids);
	//更具角色id查询角色用户中间表
	public int findUserRole(int[] ids);
	//查询该角色是否有权限信息
	public int findRoleResourceById(@Param(value="roleId") int roleId);
	//删除角色的权限信息
	public int deleteRoleResource(@Param(value="roleId") int roleId);
	//批量添加角色和权限映射信息
	public int addRoleResourceBatch(Map<String, Object> parameter);
	//添加角色和权限映射信息
	public int addRoleResource(Map<String, Object> parameter);
}
