package com.lawu.eshop.jobs.impl.ad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.jobs.JobsConfig;
import com.lawu.eshop.jobs.service.AdSrvService;

/**
 * @author meishuquan
 * @date 2017/9/14.
 */
public class RebuildAdIndexJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(RebuildAdIndexJob.class);

    @Autowired
    private AdSrvService adSrvService;

    @Autowired
    private JobsConfig jobsConfig;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        adSrvService.delInvalidAdIndex(DelIndexTypeEnum.PART);
        adSrvService.rebuildAdIndex(jobsConfig.getPageSize());

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
