package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointMonthBO;

public interface ReportAreaAdPointDailyService {

	List<ReportAreaAdPointDailyBO> selectReportAreaAdPointDaily(Integer areaId, String date);
	
	int insertReportAreaAdPointDaily(ReportAreaAdPointDailyParams param);
	
	int deleteReportAreaAdPointDaily(Long id);
	
	List<ReportAreaAdPointMonthBO> selectReportAreaAdPointDailyInMonth(String bdate, String edate);
	
	List<ReportAreaAdPointDailyBO> selectReportAreaAdPointDaily(AgentSelectAreaAdPointParam param);
}
