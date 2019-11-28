package com.webside.report.service;

import java.util.List;

import com.webside.report.model.ReportEntity;
import com.webside.util.PageData;
import com.webside.util.PageResult;

public interface ReportService {
	public PageResult<List<ReportEntity>> dayRport(PageData pd);
}
