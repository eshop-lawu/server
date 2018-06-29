package com.lawu.eshop.jobs.impl.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.WithdrawCashReportService;

/**
 * @author zhangyong
 * @date 2017/8/15.
 */
public class AgentWithdrawCashMonthReportJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(AgentWithdrawCashMonthReportJob.class);

    @Autowired
    private WithdrawCashReportService withdrawCashReportService;
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        withdrawCashReportService.executeCollectAgentMonthData();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
