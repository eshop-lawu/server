package com.lawu.eshop.jobs.impl.user;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.impl.store.StoreStatisticsJob;
import com.lawu.eshop.jobs.service.UserRegStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
public class UserRegDailyJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(StoreStatisticsJob.class);

    @Autowired
    private UserRegStatisticsService userRegStatisticsService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        userRegStatisticsService.executeCollectionUserRegDaily();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
