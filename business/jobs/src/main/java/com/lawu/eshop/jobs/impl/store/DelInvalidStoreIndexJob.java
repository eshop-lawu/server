package com.lawu.eshop.jobs.impl.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.jobs.service.MerchantStoreService;

/**
 * @author meishuquan
 * @date 2018/1/18.
 */
public class DelInvalidStoreIndexJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(DelInvalidStoreIndexJob.class);

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        merchantStoreService.delInvalidStoreIndex(DelIndexTypeEnum.PART);

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
