package com.lawu.eshop.jobs.service;

/**
 * 平台总销量扩展服务接口
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public interface ReportSalesExtendService {

	/**
	 * 定时任务<p>
	 * 保存平台总销量记录
	 * 
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	void executeSave();
	
}
