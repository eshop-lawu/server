package com.lawu.eshop.jobs.impl.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.UserRegStatisticsService;

/**
 * 按地区日统计用户注册数量
 * @author zhangyong
 * @date 2017/8/11.
 */
public class AgentUserRegDailyJob implements SimpleJob{
    private static Logger logger = LoggerFactory.getLogger(AgentUserRegDailyJob.class);

    @Autowired
    private UserRegStatisticsService userRegStatisticsService;
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        userRegStatisticsService.executeCollectionUserRegAreaDaily();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
