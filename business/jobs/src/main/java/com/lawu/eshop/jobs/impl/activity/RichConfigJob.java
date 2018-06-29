package com.lawu.eshop.jobs.impl.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.RichConfigService;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月4日
 */
public class RichConfigJob implements SimpleJob {
	
	private static Logger logger = LoggerFactory.getLogger(RichConfigJob.class);

    @Autowired
    private RichConfigService richConfigService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        richConfigService.effectiveJob();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }

}
