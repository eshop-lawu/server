package com.lawu.eshop.jobs.impl.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.JobsConfig;
import com.lawu.eshop.jobs.service.StatisticsSrvService;

public class ReportAreaPointConsumeMonthJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(ReportAreaPointConsumeMonthJob.class);

    @Autowired
    private StatisticsSrvService statisticsSrvService;

    @Autowired
    private JobsConfig jobsConfig;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        statisticsSrvService.executeCollectReportAreaPointConsumeMonth(jobsConfig.getPageSize());

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }

}
