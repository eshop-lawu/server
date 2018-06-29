package com.lawu.eshop.jobs.impl.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.eshop.jobs.JobsConfig;
import com.lawu.eshop.jobs.service.ProductService;

/**
 * @author meishuquan
 * @date 2018/1/18.
 */
public class DelInvalidProductIndexJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(DelInvalidProductIndexJob.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private JobsConfig jobsConfig;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        productService.delInvalidProductIndex(DelIndexTypeEnum.PART);

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
