package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.activity.PointLotteryNotification;
import com.lawu.eshop.mq.dto.activity.reply.PointLotteryReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.CheckRepeatOfPropertyOperationParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 活动模块，积分夺宝 - 从事务
 *
 * @author meishuquan
 * @date 2018/2/2
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_POINT_LOTTERY)
public class PointLotteryTransactionFollowServiceImpl extends AbstractTransactionFollowService<PointLotteryNotification, PointLotteryReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PointDetailService pointDetailService;

    @Override
    public void execute(PointLotteryNotification pointLotteryNotification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(pointLotteryNotification.getUserNum());
        param.setPoint(pointLotteryNotification.getPoint());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_LOTTERY);
        param.setBizId(String.valueOf(pointLotteryNotification.getId()));
        param.setGmtExecute(pointLotteryNotification.getGmtExecute());
        propertyInfoDataService.doHanlderMinusPoint(param);
    }

    @Override
    public PointLotteryReply getReply(PointLotteryNotification pointLotteryNotification) {
        CheckRepeatOfPropertyOperationParam param = new CheckRepeatOfPropertyOperationParam();
        param.setUserNum(pointLotteryNotification.getUserNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_LOTTERY);
        param.setBizIds(String.valueOf(pointLotteryNotification.getId()));
        boolean result = pointDetailService.verifyRepeatByUserNumAndTransactionTypeAndBizId(param);
        PointLotteryReply reply = new PointLotteryReply();
        reply.setResult(result);
        return reply;
    }

}
