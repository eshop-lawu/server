package com.lawu.eshop.order.srv.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 自动提醒发货
 * 
 * @author jiangxinjun
 * @createDate 2017年4月17日
 * @updateDate 2017年11月14日
 */
public class ShoppingOrderAutoRemindShipmentsJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(ShoppingOrderAutoRemindShipmentsJob.class);

    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        shoppingOrderService.executeAutoRemindShipments();
        
        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
