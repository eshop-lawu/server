package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointMonthParams;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointMonthBO;

public interface ReportAreaAdPointMonthService {

	int insertReportAreaAdPointMonth(ReportAreaAdPointMonthParams param);
	
	
	List<ReportAreaAdPointMonthBO> selectReportAreaAdPointMonth(AgentSelectAreaAdPointParam param);
}
