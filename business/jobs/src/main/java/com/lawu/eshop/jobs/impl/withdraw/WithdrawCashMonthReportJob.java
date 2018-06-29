package com.lawu.eshop.jobs.impl.withdraw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.WithdrawCashReportService;

/**
 *
 * <p>
 * Description: 平台用户提现月统计
 * </p>
 * @author Yangqh
 * @date 2017年6月28日 下午2:32:35
 *
 */
public class WithdrawCashMonthReportJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(WithdrawCashMonthReportJob.class);

    @Autowired
    private WithdrawCashReportService withdrawCashReportService;
    
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        withdrawCashReportService.executeCollectMonthData();
        
        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
