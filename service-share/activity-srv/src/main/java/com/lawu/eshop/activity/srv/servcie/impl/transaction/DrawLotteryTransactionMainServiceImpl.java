package com.lawu.eshop.activity.srv.servcie.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.constants.TransactionConstant;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityRecordService;
import com.lawu.eshop.mq.dto.activity.DrawLotteryNotification;
import com.lawu.eshop.mq.dto.activity.reply.DrawLotteryReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;


/**
 * 活动模块，积分抽奖 - 主事务
 *
 * @author meishuquan
 * @date 2018/1/15.
 */
@Service("drawLotteryTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.DRAW_LOTTERY, topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_DRAW_LOTTERY)
public class DrawLotteryTransactionMainServiceImpl extends AbstractTransactionMainService<DrawLotteryNotification, DrawLotteryReply> {

    @Autowired
    private DrawLotteryActivityRecordService drawLotteryActivityRecordService;

    @Override
    public DrawLotteryNotification selectNotification(Long id) {
        DrawLotteryActivityRecordBO recordBO = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(id);
        if (recordBO == null) {
            return null;
        }

        DrawLotteryNotification lotteryNotification = new DrawLotteryNotification();
        lotteryNotification.setId(id);
        lotteryNotification.setUserNum(recordBO.getUserNum());
        lotteryNotification.setPoint(String.valueOf(recordBO.getPayPoint().intValue()));
        lotteryNotification.setGmtExecute(recordBO.getGmtCreate());
        return lotteryNotification;
    }

    @Override
    public void afterSuccess(Long id, DrawLotteryReply reply) {
        if (reply.isResult()) {
            drawLotteryActivityRecordService.updateLotteryStatus(id, DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY);
        } else {
            drawLotteryActivityRecordService.updateLotteryStatus(id, DrawLotteryActivityRecordStatusEnum.POINT_DEDUCT_FAIL);
        }
    }

}
