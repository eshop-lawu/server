package com.lawu.eshop.statistics.srv.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaWithdrawBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaWithdrawDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportWithdrawDailyBO;

public interface WithdrawCashService {

	void saveDaily(ReportKCommonParam param);

	void saveMonth(ReportKCommonParam param);

	List<ReportWithdrawDailyBO> getDailyList(String reportDate);

	void deleteDailyByReportDate(String reportDate);

	void deleteMonthByReportDate(String reportDate);

	ReportCommonBackDTO selectReport(String bdate,String edate);

    void saveAgentDaily(AgentWithdrawCashParam param);

    List<ReportAreaWithdrawDailyBO> selectReportAreaWithdrawCashList(String month, Integer cityId);

	void saveAgentMonth(AgentWithdrawCashParam param);

    ReportAreaWithdrawBO selectAreaWithdrawDailyReport(AgentReportParam param);

	ReportAreaWithdrawBO selectAreaWithdrawMonthReport(AgentReportParam param);
	
	Date getLastReportWithdraw();
	
	Date getLastReportWithdrawMonth();
}
