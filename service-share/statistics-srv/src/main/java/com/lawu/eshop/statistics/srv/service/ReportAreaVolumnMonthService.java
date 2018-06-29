package com.lawu.eshop.statistics.srv.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.statistics.param.ReportAreaVolumnMonthParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnMonthBO;

public interface ReportAreaVolumnMonthService {

	int insertReportAreaVolumnMonth(ReportAreaVolumnMonthParam param);
	
	List<ReportAreaVolumnMonthBO> selectReportAreaVolumnMonth(Integer cityId, Date bdate, Date edate);
}
