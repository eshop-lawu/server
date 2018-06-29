package com.lawu.eshop.jobs.impl.store;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.StoreStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author meishuquan
 * @date 2017/4/25.
 */
public class StoreStatisticsJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(StoreStatisticsJob.class);

    @Autowired
    private StoreStatisticsService storeStatisticsService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        storeStatisticsService.executeStoreStatistics();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
