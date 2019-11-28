package com.webside.role.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.webside.plateformaccount.model.UserEntity;
import com.webside.role.model.RoleEntity;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

public interface RoleService {
    
    public List<RoleEntity> findAll(PageData pd);
    public PageResult<List<RoleEntity>> findPage(PageData pd);
    public RoleEntity findByName(String name);
    public void add(RoleEntity roleEntity);
    public RoleEntity findById(Integer id);
    public void update(RoleEntity roleEntity);
    public JsonResult delete(int[] ids);
    public boolean addRolePermBatch(int roleId, List<Integer> ids);
    public boolean addRolePerm(Integer roleId, Long resourceId);
}