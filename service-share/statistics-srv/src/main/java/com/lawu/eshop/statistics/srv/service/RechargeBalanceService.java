package com.lawu.eshop.statistics.srv.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.bo.RechargeBalanceDailyBO;

public interface RechargeBalanceService {

	void saveDaily(ReportKCommonParam param);

	void saveMonth(ReportKCommonParam param);

	List<RechargeBalanceDailyBO> getDailyList(String reportDate);

	void deleteDailyByReportDate(String reportDate);

	void deleteMonthByReportDate(String reportDate);

	ReportCommonBackDTO selectReport(String bdate, String edate);
	
	Date getLastRechargeDay();
	
	Date getLastRechargeMonth();
}
