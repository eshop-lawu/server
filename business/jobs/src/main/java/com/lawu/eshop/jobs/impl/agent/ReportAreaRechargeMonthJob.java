package com.lawu.eshop.jobs.impl.agent;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.AgentReportAreaRechargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 按地区日统计用户商家充值
 * @author yangqh
 * @date 2017/8/14.
 */
public class ReportAreaRechargeMonthJob implements SimpleJob{
    private static Logger logger = LoggerFactory.getLogger(ReportAreaRechargeMonthJob.class);

    @Autowired
    private AgentReportAreaRechargeService agentReportAreaRechargeService;
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        agentReportAreaRechargeService.executeCollectMonthData();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
