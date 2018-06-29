package com.lawu.eshop.statistics.srv.mapper.extend;

import com.lawu.eshop.statistics.srv.domain.extend.ReportNewDateDOView;

/**
 * @author zhangrc
 * @date 2017/9/8.
 */
public interface PointConsumeMonthDOMapperExtend {
	
	ReportNewDateDOView getReportDatePointConsumeDaily();
	
	ReportNewDateDOView getReportDatePointConsumeMonth();

}
