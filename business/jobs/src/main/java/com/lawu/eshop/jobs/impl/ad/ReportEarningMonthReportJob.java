package com.lawu.eshop.jobs.impl.ad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.ReportEarningExtendService;

public class ReportEarningMonthReportJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(ReportEarningMonthReportJob.class);

    @Autowired
    private ReportEarningExtendService reportEarningExtendService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        reportEarningExtendService.executeCollectMonthData();
        
        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
