package com.lawu.eshop.jobs.impl;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.SummaryIncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇总每日收益
 *
 * @author yangqh
 * @date 2018年3月9日
 */
public class SummaryIncomeJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(SummaryIncomeJob.class);

    @Autowired
    private SummaryIncomeService summaryIncomeService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        summaryIncomeService.execute();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }

}
