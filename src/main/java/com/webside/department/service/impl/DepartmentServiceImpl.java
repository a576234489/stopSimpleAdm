package com.webside.department.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.base.baseservice.impl.AbstractService;
import com.webside.department.mapper.DepartmentMapper;
import com.webside.department.model.DepartmentEntity;
import com.webside.department.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl extends AbstractService<DepartmentEntity,Long> implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(departmentMapper);
	}

	@Override
	public List<DepartmentEntity> queryTreeGridListByPage(Map<String, Object> parameters) {
		return departmentMapper.queryTreeGridListByPage(parameters);
	}

}
