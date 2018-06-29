package com.lawu.eshop.jobs.impl.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.ReportAreaVolumnDailyService;

public class ReportAreaVolumnDailyJob implements SimpleJob {

	private static Logger logger = LoggerFactory.getLogger(ReportAreaVolumnDailyJob.class);
	
	@Autowired
	private ReportAreaVolumnDailyService reportAreaVolumnDailyService;
	
	@Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{executeCollectAreaVolumnDaily}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
		
		reportAreaVolumnDailyService.executeCollectAreaVolumnDaily();
		
		logger.debug("------{executeCollectAreaVolumnDaily}-{} ending------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}

}
