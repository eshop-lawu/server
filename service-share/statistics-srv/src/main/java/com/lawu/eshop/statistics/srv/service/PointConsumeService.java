package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.bo.PointConsumeDailyBO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportNewDateDOView;
import java.util.Date;

public interface PointConsumeService {

	void saveDaily(ReportKCommonParam param);

	void saveMonth(ReportKCommonParam param);

	List<PointConsumeDailyBO> getDailyList(String reportDate);

	void deleteDailyByReportDate(String reportDate);

	void deleteMonthByReportDate(String reportDate);

	ReportCommonBackDTO selectReport(String bdate, String edate);

	Date getDaily();
	
	Date getMonth();
	
	/**
     * 获取日统计积分消费最后一条数据统计时间
     * @author zhangrc
     * @date 2017/09/08
     * @return
     */
    ReportNewDateDOView getReportDatePointConsumeDaily();
    
    
    /**
     * 获取月统计积分消费最后一条数据统计时间
     * @author zhangrc
     * @date 2017/09/08
     * @return
     */
    ReportNewDateDOView getReportDatePointConsumeMonth();

}
