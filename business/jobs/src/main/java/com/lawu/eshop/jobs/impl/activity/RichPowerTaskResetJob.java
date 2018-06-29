package com.lawu.eshop.jobs.impl.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.RichPowerTaskResetService;

/**
 * @author zhangyong
 * @date 2018/5/7.
 */
public class RichPowerTaskResetJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(RichPowerTaskResetJob.class);
    @Autowired
    private RichPowerTaskResetService richPowerTaskResetService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        richPowerTaskResetService.executeTaskResetJob();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
