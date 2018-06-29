package com.lawu.eshop.jobs.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.JobsConfig;
import com.lawu.eshop.jobs.impl.store.StoreStatisticsJob;
import com.lawu.eshop.jobs.service.FansInviteContentService;

/**
 * @author meishuquan
 * @date 2017/9/22.
 */
public class UserFansJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(StoreStatisticsJob.class);

    @Autowired
    private FansInviteContentService fansInviteContentService;

    @Autowired
    private JobsConfig jobsConfig;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        fansInviteContentService.dealOverdueFansInvite(jobsConfig.getPageSize());

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
