package com.lawu.eshop.jobs.impl.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.LotteryActivityService;

/**
 * @author meishuquan
 * @date 2017/11/27.
 */
public class LotteryActivityJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(LotteryActivityJob.class);

    @Autowired
    private LotteryActivityService lotteryActivityService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        lotteryActivityService.executeUpdateLotteryActivityStatus();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
