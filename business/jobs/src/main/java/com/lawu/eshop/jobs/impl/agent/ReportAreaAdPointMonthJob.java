package com.lawu.eshop.jobs.impl.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.ReportAreaAdPointMonthService;

public class ReportAreaAdPointMonthJob implements SimpleJob {
	
	private static Logger logger = LoggerFactory.getLogger(ReportAreaAdPointMonthJob.class);

    @Autowired
    private ReportAreaAdPointMonthService reportAreaAdPointMonthService;
    
    @Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{hhhhhhhhhhhh}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

		reportAreaAdPointMonthService.executeCollectReportAreaAdPointMonth();
		
		logger.debug("------{hhhhhhhhhhh}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}

}
