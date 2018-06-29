package com.lawu.eshop.activity.srv.servcie.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.constants.TransactionConstant;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityPrizeService;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityRecordService;
import com.lawu.eshop.common.constants.PayTypeEnum;
import com.lawu.eshop.mq.dto.activity.TakeLotteryNotification;
import com.lawu.eshop.mq.dto.activity.reply.TakeLotteryReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;


/**
 * 活动模块，领奖(余额/积分) - 主事务
 *
 * @author meishuquan
 * @date 2018/2/28.
 */
@Service("takeLotteryTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.TAKE_LOTTERY, topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_TAKE_LOTTERY)
public class TakeLotteryTransactionMainServiceImpl extends AbstractTransactionMainService<TakeLotteryNotification, TakeLotteryReply> {

    @Autowired
    private DrawLotteryActivityRecordService drawLotteryActivityRecordService;

    @Autowired
    private DrawLotteryActivityPrizeService drawLotteryActivityPrizeService;

    @Override
    public TakeLotteryNotification selectNotification(Long id) {
        DrawLotteryActivityRecordBO recordBO = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(id);
        if (recordBO == null) {
            return null;
        }
        DrawLotteryActivityPrizeBO prizeBO = drawLotteryActivityPrizeService.getPrizeDetail(recordBO.getDrawLotteryActivityPrizeId());
        if (prizeBO == null) {
            return null;
        }

        TakeLotteryNotification lotteryNotification = new TakeLotteryNotification();
        lotteryNotification.setId(id);
        lotteryNotification.setUserNum(recordBO.getUserNum());
        lotteryNotification.setMoney(String.valueOf(prizeBO.getPrice()));
        lotteryNotification.setPayTypeEnum(PayTypeEnum.getEnum(prizeBO.getPrizeType()));
        lotteryNotification.setGmtExecute(recordBO.getGmtCreate());
        return lotteryNotification;
    }

    @Override
    public void afterSuccess(Long id, TakeLotteryReply reply) {
        if (reply.isResult()) {
            drawLotteryActivityRecordService.updateLotteryStatus(id, DrawLotteryActivityRecordStatusEnum.SEND_LOTTERY);
        }
    }

}
