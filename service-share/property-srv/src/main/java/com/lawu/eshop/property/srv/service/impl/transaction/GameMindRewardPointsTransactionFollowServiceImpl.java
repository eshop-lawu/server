package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.game.DeductionPointsNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 头脑PK奖励积分-从模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_MIND_INCREASE_POINTS)
public class GameMindRewardPointsTransactionFollowServiceImpl extends
        AbstractTransactionFollowService<DeductionPointsNotification, Reply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;
    
    @Override
    public void execute(DeductionPointsNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(notification.getMemberNum());
        param.setPoint(String.valueOf(notification.getPoint().intValue()));
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_QUESTION_IN);
        param.setBizId(String.valueOf(notification.getBizId()));
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderAddPoint(param);
    }
}
