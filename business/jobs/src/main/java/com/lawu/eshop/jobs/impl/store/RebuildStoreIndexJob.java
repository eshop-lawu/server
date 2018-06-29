package com.lawu.eshop.jobs.impl.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.MerchantStoreService;
import com.lawu.eshop.jobs.service.RebuildStoreService;

/**
 * @author meishuquan
 * @date 2017/10/16.
 */
public class RebuildStoreIndexJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(RebuildStoreIndexJob.class);

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private RebuildStoreService rebuildStoreService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        rebuildStoreService.rebuildStoreService();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
