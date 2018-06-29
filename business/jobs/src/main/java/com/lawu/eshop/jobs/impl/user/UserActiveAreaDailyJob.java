package com.lawu.eshop.jobs.impl.user;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.UserActiveStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public class UserActiveAreaDailyJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(UserActiveAreaDailyJob.class);
    @Autowired
    private UserActiveStatisticsService userActiveStatisticsService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        userActiveStatisticsService.executeCollectionUserActiveAreaDaily();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

    }
}
