package com.webside.department.service;

import java.util.List;
import java.util.Map;

import com.webside.base.baseservice.BaseService;
import com.webside.department.model.DepartmentEntity;

public interface DepartmentService extends BaseService<DepartmentEntity,Long> {

	public List<DepartmentEntity> queryTreeGridListByPage(Map<String,Object> parameters);
}
