package com.lawu.eshop.statistics.srv.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.statistics.param.ReportEarningParam;
import com.lawu.eshop.statistics.srv.bo.ReportCommonEarningsBO;
import com.lawu.eshop.statistics.srv.bo.ReportEarningsDailyBO;

public interface ReportEarningService {

	void saveDaily(ReportEarningParam param);

	void saveMonth(ReportEarningParam param);
	
	List<ReportEarningsDailyBO> getDailyList(String reportDate);
	
	ReportCommonEarningsBO selectReport(String bdate, String edate);
	
	Date getDaily();
	
	Date getMonth();
}
