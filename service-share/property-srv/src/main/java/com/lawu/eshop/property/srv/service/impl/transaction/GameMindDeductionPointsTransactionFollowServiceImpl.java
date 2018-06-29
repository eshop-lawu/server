package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.game.DeductionPointsNotification;
import com.lawu.eshop.mq.dto.game.reply.DeductionPointsReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.CheckRepeatOfPropertyOperationParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 头脑PK扣除积分-从模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月13日
 * @updateDate 2018年3月13日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_MIND_DEDUCTION_POINTS)
public class GameMindDeductionPointsTransactionFollowServiceImpl extends AbstractTransactionFollowService<DeductionPointsNotification, DeductionPointsReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PointDetailService pointDetailService;

    @Override
    public void execute(DeductionPointsNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(notification.getMemberNum());
        param.setPoint(notification.getPoint().toString());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_QUESTION);
        param.setBizId(String.valueOf(notification.getBizId()));
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderMinusPoint(param);
    }

    @Override
    public DeductionPointsReply getReply(DeductionPointsNotification notification) {
        CheckRepeatOfPropertyOperationParam param = new CheckRepeatOfPropertyOperationParam();
        param.setUserNum(notification.getMemberNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_QUESTION);
        param.setBizIds(String.valueOf(notification.getBizId()));
        boolean result = pointDetailService.verifyRepeatByUserNumAndTransactionTypeAndBizId(param);
        DeductionPointsReply reply = new DeductionPointsReply();
        reply.setIsSuccess(result);
        return reply;
    }

}
