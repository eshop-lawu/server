package com.lawu.eshop.jobs.impl.user;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.UserVisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangyong
 * @date 2017/7/3.
 */
public class UserVisitJob  implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(UserVisitJob.class);
    @Autowired
    private UserVisitService userVisitService;
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        userVisitService.executeAddUserVisitRecords();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
