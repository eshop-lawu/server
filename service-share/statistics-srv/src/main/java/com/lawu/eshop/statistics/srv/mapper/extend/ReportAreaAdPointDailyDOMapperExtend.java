package com.lawu.eshop.statistics.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaAdPointDailyDOView;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaAdPointDailyInMonthDOView;

public interface ReportAreaAdPointDailyDOMapperExtend {

	List<ReportAreaAdPointDailyInMonthDOView> selectReportAreaAdPointDailyInMonth(@Param("bdate") String bdate,@Param("edate") String edate);

	List<ReportAreaAdPointDailyDOView> selectReportAreaAdPointDaily(AgentSelectAreaAdPointParam param);
	
}
