package com.lawu.eshop.jobs.impl.analyze;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.AbnormalActivityMemberService;

/**
 * @author zhangyong
 * @date 2018/3/1.
 */
public class SuspectedActivityMemberJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(SuspectedActivityMemberJob.class);

    @Autowired
    private AbnormalActivityMemberService abnormalActivityMemberService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        abnormalActivityMemberService.executeUpdateActivitySuspectedStatus();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
