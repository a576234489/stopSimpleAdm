package com.webside.report.mapper;

import java.util.List;

import com.webside.report.model.ReportEntity;
import com.webside.util.PageData;

public interface ReportMapper {
	public List<ReportEntity> findReport(PageData pd);
	public List<ReportEntity> findHandleCard(PageData pd);
	public ReportEntity findReportAll(PageData pd);
	public ReportEntity findHandleCardAll(PageData pd);
}
