package com.webside.role.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.baseservice.impl.AbstractService;
import com.webside.exception.ServiceException;
import com.webside.plateformaccount.model.UserEntity;
import com.webside.role.mapper.RoleMapper;
import com.webside.role.model.RoleEntity;
import com.webside.role.service.RoleService;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.JsonResult;
import com.webside.util.PageData;
import com.webside.util.PageResult;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<RoleEntity> findAll(PageData pd) {
		return roleMapper.findAll(pd);
	}

	@Override
	public PageResult<List<RoleEntity>> findPage(PageData pd) {
		Page<Object> page = PageHelper.startPage(Integer.parseInt(pd.get("page").toString()), Integer.parseInt(pd.get("limit").toString()));
		List<RoleEntity> list = roleMapper.findPage(pd);
		return new PageResult<List<RoleEntity>>("", list, 0, (int) page.getTotal());
	}

	@Override
	public RoleEntity findByName(String name) {
		return roleMapper.findByName(name);
	}

	@Override
	public void add(RoleEntity roleEntity) {
		roleEntity.setCreateTime(new Date(System.currentTimeMillis()));
		roleEntity.setStatus(0);
		roleMapper.insert(roleEntity);
	}

	@Override
	public RoleEntity findById(Integer id) {
		return roleMapper.findById(id);
	}

	@Override
	public void update(RoleEntity roleEntity) {
		roleMapper.update(roleEntity);
	}

	@Override
	public JsonResult delete(int[] ids) {
		int i = roleMapper.findUserRole(ids);
		if(i>0) {
			return new JsonResult(false, null, "所选择得角色下面存在用户,不能删除");
		}
		try{
			roleMapper.deleteBatchById(ids);
			return new JsonResult(true, null, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult(false, null, "删除失败");
		}
	}

	@Override
	public boolean addRolePermBatch(int id, List<Integer> ids) {
		boolean flag = false;
		try {
			int permCount = roleMapper.findRoleResourceById(id);
			boolean delFlag = true;
			if (permCount > 0) {
				int delResult = roleMapper.deleteRoleResource(id);
				if (permCount != delResult) {
					delFlag = false;
				}
			}

			if (delFlag) {
				if (ids.size() > 0) {
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("roleId", id);
					parameter.put("resourceIds", ids);
					int addResult = roleMapper.addRoleResourceBatch(parameter);
					if (addResult == ids.size()) {
						flag = true;
					}
				} else {
					flag = true;
				}
			}
			ShiroAuthenticationManager.clearAllUserAuth();
			return flag;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	
	}

	@Override
	public boolean addRolePerm(Integer roleId, Long resourceId) {
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("roleId", roleId);
			parameter.put("resourceId", resourceId);
			System.out.println( "addRoleResource {[(" );
			return roleMapper.addRoleResource(parameter) > 0 ? true : false;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
