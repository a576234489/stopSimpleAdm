package com.webside.department.mapper;

import java.util.List;
import java.util.Map;

import com.webside.base.basemapper.BaseMapper;
import com.webside.department.model.DepartmentEntity;

public interface DepartmentMapper extends BaseMapper<DepartmentEntity, Long> {

	public List<DepartmentEntity> queryTreeGridListByPage(Map<String,Object> parameters);
}
