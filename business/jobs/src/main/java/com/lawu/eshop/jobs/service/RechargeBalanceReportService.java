package com.lawu.eshop.jobs.service;

public interface RechargeBalanceReportService {

	/**
	 * 定时采集数据
	 * 
	 * @author yangqh
	 * @date 2017年6月29日 下午5:07:06
	 */
	void executeCollectDailyData();

	/**
	 * 定时采集数据
	 * 
	 * @author yangqh
	 * @date 2017年6月29日 下午5:07:10
	 */
	void executeCollectMonthData();

	

}
