package com.lawu.eshop.jobs.impl.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.GamePointReportJobService;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
public class GamePointReportDailyJob implements SimpleJob {

	 private static Logger logger = LoggerFactory.getLogger(GamePointReportDailyJob.class);

	    @Autowired
	    private GamePointReportJobService gamePointReportJobService;
	    
	    @Override
	    public void execute(ShardingContext shardingContext) {
	        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

	        gamePointReportJobService.executeGamePointReportDaily();
	        
	        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	    }
}
