package com.lawu.eshop.statistics.srv.service;

import java.util.List;


import com.lawu.eshop.statistics.param.ReportAreaVolumnDailyParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnDailyInMonthBO;

public interface ReportAreaVolumnDailyService {

	
	int insertReportAreaVolumnDaily(ReportAreaVolumnDailyParam param);
	
	
	List<ReportAreaVolumeDailyBO> selectReportAreaVolumnDaily(Integer cityId, String bdate, String edate);
	
	
	List<ReportAreaVolumnDailyInMonthBO> selectReportAreaVolumeDailyInMonth(String bdate, String edate);
}
