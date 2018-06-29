package com.lawu.eshop.jobs.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.ReportSalesExtendService;

/**
 * 定时任务统计平台总销量
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class ReportSalesJob implements SimpleJob {

	private static Logger logger = LoggerFactory.getLogger(ReportSalesJob.class);

	@Autowired
	private ReportSalesExtendService reportSalesExtendService;

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

		reportSalesExtendService.executeSave();

		logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}
}
