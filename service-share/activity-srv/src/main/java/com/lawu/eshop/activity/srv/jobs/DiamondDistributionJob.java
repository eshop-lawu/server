package com.lawu.eshop.activity.srv.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.activity.srv.servcie.RichDiamondRecordService;

/**
 * 钻石分配定时任务
 * @author jiangxinjun
 * @createDate 2018年5月3日
 * @updateDate 2018年5月3日
 */
public class DiamondDistributionJob implements SimpleJob {
    
    private static final Logger logger = LoggerFactory.getLogger(DiamondDistributionJob.class);

    @Autowired
    private RichDiamondRecordService richDiamondRecordService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("diamondDistribution start");
        richDiamondRecordService.diamondDistribution();
        logger.debug("diamondDistribution end");
    }

}
