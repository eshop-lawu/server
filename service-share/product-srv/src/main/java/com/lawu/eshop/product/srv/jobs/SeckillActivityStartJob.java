package com.lawu.eshop.product.srv.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.product.srv.service.SeckillActivityService;

/**
 * 秒杀活动开始修改抢购活动的状态
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
public class SeckillActivityStartJob implements SimpleJob {
    
    private static Logger logger = LoggerFactory.getLogger(SeckillActivityStartJob.class);

    @Autowired
    private SeckillActivityService seckillActivityService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        seckillActivityService.seckillActivityStart();
        
        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
