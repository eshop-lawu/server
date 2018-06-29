package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.eshop.statistics.srv.bo.AgentAreaRechargeQReturnBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaRechargeDailyBO;

public interface ReportAreaRechargeService {

	void saveDaily(List<AgentReportRechargeSaveParam> saveParams);

	void saveMonth(List<AgentReportRechargeSaveParam> saveParams);

	List<ReportAreaRechargeDailyBO> getDailyList(String reportDate);

	void deleteDailyByReportDate(String reportDate);

	void deleteMonthByReportDate(String reportDate);

	AgentAreaRechargeQReturnBO getAreaRechargeList(AgentReportParam param);

	

}
