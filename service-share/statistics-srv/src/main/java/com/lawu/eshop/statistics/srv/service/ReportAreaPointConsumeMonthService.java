package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.ReportAreaPointConsumeMonthParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeMonthBO;

public interface ReportAreaPointConsumeMonthService {
	
	int saveReportAreaPointConsumeMonth(ReportAreaPointConsumeMonthParam param);
	
	List<ReportAreaPointConsumeMonthBO> selectReportAreaPointConsumeMonth(Integer cityId, String bdate, String edate);

	void executeCollectReportAreaPointConsumeMonth(Integer pageSize);
}
