package com.lawu.eshop.mall.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mall.srv.service.LotteryRecordService;
import com.lawu.eshop.mq.dto.mall.LotteryRecordNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 线下抽奖兑换积分 - 从事务
 *
 * @author meishuquan
 * @date 2017/11/23.
 */
@Service("lotteryRecordTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_POINT_CONVERT_LOTTERY)
public class LotteryRecordTransactionFollowServiceImpl extends AbstractTransactionFollowService<LotteryRecordNotification, Reply> {

    @Autowired
    private LotteryRecordService lotteryRecordService;

    @Override
    public void execute(LotteryRecordNotification notification) {
        lotteryRecordService.updateLotteryCountByUserNumAndLotteryActivityId(notification.getUserNum(), notification.getLotteryActivityId());
    }

}
