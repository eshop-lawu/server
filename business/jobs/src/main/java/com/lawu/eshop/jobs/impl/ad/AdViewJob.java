package com.lawu.eshop.jobs.impl.ad;

import com.lawu.eshop.jobs.service.AdViewExtendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 定时改变广告浏览次数
 * @author zhangrc
 *
 */
public class AdViewJob implements SimpleJob{

	private static Logger logger = LoggerFactory.getLogger(AdViewJob.class);

    @Autowired
    private AdViewExtendService adViewExtendService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        adViewExtendService.updateViewCount();
        
        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }

}
