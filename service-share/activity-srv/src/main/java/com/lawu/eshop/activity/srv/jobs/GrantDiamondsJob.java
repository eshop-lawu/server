package com.lawu.eshop.activity.srv.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.activity.srv.servcie.RichDiamondRecordService;

/**
 * 钻石发放定时任务
 * @author jiangxinjun
 * @createDate 2018年5月3日
 * @updateDate 2018年5月3日
 */
public class GrantDiamondsJob implements SimpleJob {
    
    private static final Logger logger = LoggerFactory.getLogger(GrantDiamondsJob.class);
    
    @Autowired
    private RichDiamondRecordService richDiamondRecordService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("grantDiamonds start");
        richDiamondRecordService.grantDiamonds();
        logger.debug("grantDiamonds end");
    }

}
