package com.lawu.eshop.jobs.impl.payorder;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.service.PayOrderAutoCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangyong
 * @date 2017/7/28.
 */
public class PayOrderAutoCommentJob implements SimpleJob{
    private static Logger logger = LoggerFactory.getLogger(PayOrderAutoCommentJob.class);

    @Autowired
    private PayOrderAutoCommentService payOrderAutoCommentService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        // 设置超时评价订单为已评价，发送MQ消息保存好评记录
        payOrderAutoCommentService.executeAutoComment();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }
}
