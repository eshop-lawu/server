package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.activity.RedpacketSendNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 活动模块，红包发放(进余额)-从模块
 * 
 * @author jiangxinjun
 * @createDate 2018年1月16日
 * @updateDate 2018年1月16日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_RED_PACKET_SEND)
public class RedpacketSendTransactionFollowServiceImpl extends AbstractTransactionFollowService<RedpacketSendNotification, Reply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Override
    public void execute(RedpacketSendNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setPoint(notification.getRedpacketAmount().toString());
        param.setUserNum(notification.getUserNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.PLATFORM_RED_SWEEP_ACTIVITY);
        param.setTransactionDesc(MemberTransactionTypeEnum.PLATFORM_RED_SWEEP_ACTIVITY.getDescPrefix());
        param.setLoveTypeEnum(LoveTypeEnum.RED_PACKAGE);
        param.setTempBizId(notification.getAttendDetailId() == null ? "0" : notification.getAttendDetailId().toString());
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderBalanceIncome(param);
    }
}
