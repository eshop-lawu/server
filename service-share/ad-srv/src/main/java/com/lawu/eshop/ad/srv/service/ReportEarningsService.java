package com.lawu.eshop.ad.srv.service;

import java.util.List;

import com.lawu.eshop.ad.param.AdReportParam;
import com.lawu.eshop.ad.srv.bo.ReportEarningsBO;

/**
 * 广告收益详情
 * @author zhangrc
 *
 */
public interface ReportEarningsService {
	
	
	
	List<ReportEarningsBO>  getReportEarnings(AdReportParam param);
	
	
	List<ReportEarningsBO>  getReportMonthEarnings();
	
	

}
