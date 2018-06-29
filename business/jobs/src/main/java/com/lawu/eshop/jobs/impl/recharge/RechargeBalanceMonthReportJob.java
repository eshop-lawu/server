package com.lawu.eshop.jobs.impl.recharge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.RechargeBalanceReportService;

/**
 * 
 * <p>
 * Description: 充值余额统计（按月）
 * </p>
 * @author Yangqh
 * @date 2017年6月29日 下午5:07:52
 *
 */
public class RechargeBalanceMonthReportJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(RechargeBalanceMonthReportJob.class);

    @Autowired
    private RechargeBalanceReportService rechargeBalanceReportService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        rechargeBalanceReportService.executeCollectMonthData();
        
        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
