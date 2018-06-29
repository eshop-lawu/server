package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.eshop.statistics.param.ReportAdEarningsQueryParam;
import com.lawu.eshop.statistics.srv.bo.ReportAdEarningsBO;
import com.lawu.framework.core.page.Page;

/**
 * 广告收益统计接口
 * @author zhangrc
 * @date 2017/06/29
 * @version 1.2.1
 *
 */
public interface ReportAdEarningsService {
	
	/**
	 * 保存统计广告收益
	 * @param reportAdEarningsParam
	 */
	void saveReportAdEarnings(ReportAdEarningsParam reportAdEarningsParam);
	
	/**
	 * 统计列表
	 * @param query
	 * @return
	 */
	Page<ReportAdEarningsBO> selectReportAdEarnings(ReportAdEarningsQueryParam query);
	
	
	
	/**
	 * 查询已存在的广告
	 * @return
	 */
	List<Long> getReportAdEarningsIds();
	
	
	

}
