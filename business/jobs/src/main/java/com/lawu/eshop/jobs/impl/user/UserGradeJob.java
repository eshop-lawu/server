package com.lawu.eshop.jobs.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.UserGradeService;

/**
 * @author meishuquan
 * @date 2018/1/23.
 */
public class UserGradeJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(UserGradeJob.class);

    @Autowired
    private UserGradeService userGradeService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        userGradeService.updateFreeLotteryCount();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }

}
