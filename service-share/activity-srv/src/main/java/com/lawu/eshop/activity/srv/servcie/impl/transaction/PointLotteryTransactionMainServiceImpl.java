package com.lawu.eshop.activity.srv.servcie.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityOrderBO;
import com.lawu.eshop.activity.srv.constants.TransactionConstant;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityOrderService;
import com.lawu.eshop.mq.dto.activity.PointLotteryNotification;
import com.lawu.eshop.mq.dto.activity.reply.PointLotteryReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;


/**
 * 活动模块，积分夺宝 - 主事务
 *
 * @author meishuquan
 * @date 2018/2/2.
 */
@Service("pointLotteryTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.POINT_LOTTERY, topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_POINT_LOTTERY)
public class PointLotteryTransactionMainServiceImpl extends AbstractTransactionMainService<PointLotteryNotification, PointLotteryReply> {

    @Autowired
    private PointLotteryActivityOrderService pointLotteryActivityOrderService;

    @Override
    public PointLotteryNotification selectNotification(Long id) {
        PointLotteryActivityOrderBO orderBO = pointLotteryActivityOrderService.getPointLotteryActivityOrder(id);
        if (orderBO == null) {
            return null;
        }

        PointLotteryNotification lotteryNotification = new PointLotteryNotification();
        lotteryNotification.setId(id);
        lotteryNotification.setUserNum(orderBO.getUserNum());
        lotteryNotification.setPoint(String.valueOf(orderBO.getPayPoint().intValue()));
        lotteryNotification.setGmtExecute(orderBO.getGmtCreate());
        return lotteryNotification;
    }

    @Override
    public void afterSuccess(Long id, PointLotteryReply reply) {
        if (reply.isResult()) {
            pointLotteryActivityOrderService.updateLotteryOrderAndRecord(id, PointLotteryActivityOrderStatusEnum.SUCCESS);
        } else {
            pointLotteryActivityOrderService.updateLotteryOrderAndRecord(id, PointLotteryActivityOrderStatusEnum.FAILURE);
        }
    }

}
