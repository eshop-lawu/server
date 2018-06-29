package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.activity.DrawLotteryNotification;
import com.lawu.eshop.mq.dto.activity.reply.DrawLotteryReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.CheckRepeatOfPropertyOperationParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 活动模块，积分抽奖 - 从事务
 *
 * @author meishuquan
 * @date 2018/1/15
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_DRAW_LOTTERY)
public class DrawLotteryTransactionFollowServiceImpl extends AbstractTransactionFollowService<DrawLotteryNotification, DrawLotteryReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PointDetailService pointDetailService;

    @Override
    public void execute(DrawLotteryNotification drawLotteryNotification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(drawLotteryNotification.getUserNum());
        param.setPoint(drawLotteryNotification.getPoint());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_CONVERT_LOTTERY);
        param.setBizId(String.valueOf(drawLotteryNotification.getId()));
        param.setGmtExecute(drawLotteryNotification.getGmtExecute());
        propertyInfoDataService.doHanlderMinusPoint(param);
    }

    @Override
    public DrawLotteryReply getReply(DrawLotteryNotification drawLotteryNotification) {
        CheckRepeatOfPropertyOperationParam param = new CheckRepeatOfPropertyOperationParam();
        param.setUserNum(drawLotteryNotification.getUserNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_CONVERT_LOTTERY);
        param.setBizIds(String.valueOf(drawLotteryNotification.getId()));
        boolean result = pointDetailService.verifyRepeatByUserNumAndTransactionTypeAndBizId(param);
        DrawLotteryReply reply = new DrawLotteryReply();
        reply.setResult(result);
        return reply;
    }

}
