package com.lawu.eshop.jobs.impl.store;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.RecommendStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author meishuquan
 * @date 2017/7/27.
 */
public class RecommendStoreJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(RecommendStoreJob.class);

    @Autowired
    private RecommendStoreService recommendStoreService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        recommendStoreService.executeRecommendStoreStatistics();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
