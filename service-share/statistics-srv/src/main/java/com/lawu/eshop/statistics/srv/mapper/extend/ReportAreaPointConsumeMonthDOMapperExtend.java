package com.lawu.eshop.statistics.srv.mapper.extend;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaPointConsumeDailyInMonthDOView;

public interface ReportAreaPointConsumeMonthDOMapperExtend {

	List<ReportAreaPointConsumeDailyInMonthDOView> selectReportAreaPointConsumeDailyInMonth(@Param("bdate") String bdate, @Param("edate") String edate);

	List<ReportAreaPointConsumeDailyInMonthDOView> executeCollectReportAreaPointConsumeMonth(Map<String,Object> map);

}
