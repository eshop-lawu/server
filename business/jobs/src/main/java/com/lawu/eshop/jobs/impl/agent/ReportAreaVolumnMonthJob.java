package com.lawu.eshop.jobs.impl.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.ReportAreaVolumnMonthService;

public class ReportAreaVolumnMonthJob implements SimpleJob {

	private static Logger logger = LoggerFactory.getLogger(ReportAreaVolumnMonthJob.class);
	
	@Autowired
	private ReportAreaVolumnMonthService reportAreaVolumnMonthService;
	
	@Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{executeCollectAreaVolumnMonth}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
		
		reportAreaVolumnMonthService.executeCollectAreaVolumnMonth();
		
		logger.debug("------{executeCollectAreaVolumnMonth}-{} ending------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}

}
