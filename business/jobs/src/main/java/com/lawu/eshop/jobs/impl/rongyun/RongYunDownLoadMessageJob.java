package com.lawu.eshop.jobs.impl.rongyun;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.RongYunDownLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangyong
 * @date 2017/5/22.
 */
public class RongYunDownLoadMessageJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(RongYunDownLoadMessageJob.class);
    @Autowired
    private RongYunDownLoadService rongYunDownLoadService;
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        rongYunDownLoadService.downLoadMessageFile();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
