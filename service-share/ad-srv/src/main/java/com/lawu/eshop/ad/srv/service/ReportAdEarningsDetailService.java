package com.lawu.eshop.ad.srv.service;

import com.lawu.eshop.ad.param.ReportAdEarningsDetailParam;
import com.lawu.eshop.ad.srv.bo.ReportAdEarningsDetailBO;
import com.lawu.framework.core.page.Page;

/**
 * 广告收益详情
 * @author zhangrc
 *
 */
public interface ReportAdEarningsDetailService {
	
	
	/**
	 * 广告收益详情记录
	 * @param param
	 * @return
	 */
	Page<ReportAdEarningsDetailBO>  getReportAdEarningsDetail(ReportAdEarningsDetailParam param);
	
	

}
