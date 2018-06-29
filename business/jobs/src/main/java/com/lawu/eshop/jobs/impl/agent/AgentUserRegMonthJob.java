package com.lawu.eshop.jobs.impl.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.UserRegStatisticsService;

/**
 * @author zhangyong
 * @date 2017/8/11.
 */
public class AgentUserRegMonthJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(AgentUserRegMonthJob.class);
    @Autowired
    private UserRegStatisticsService userRegStatisticsService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        userRegStatisticsService.executeCollectionUserRegAreaMonth();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
