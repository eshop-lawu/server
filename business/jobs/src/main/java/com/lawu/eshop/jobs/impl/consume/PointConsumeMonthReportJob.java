package com.lawu.eshop.jobs.impl.consume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.PointConsumeReportService;

/**
 * 
 * <p>
 * Description: 积分消费（按月）
 * </p>
 * @author Yangqh
 * @date 2017年6月29日 下午5:07:52
 *
 */
public class PointConsumeMonthReportJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(PointConsumeMonthReportJob.class);

    @Autowired
    private PointConsumeReportService pointConsumeReportService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        pointConsumeReportService.executeCollectMonthData();
        
        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
