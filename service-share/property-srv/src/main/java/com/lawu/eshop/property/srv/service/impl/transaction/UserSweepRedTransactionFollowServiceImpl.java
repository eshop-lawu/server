package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.ad.AdPointNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 用户领取商家发的红包---从事务
 *
 * @author zhangrc
 * @date 2017/4/12
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_SWEEP_RED)
public class UserSweepRedTransactionFollowServiceImpl extends AbstractTransactionFollowService<AdPointNotification, Reply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Override
    public void execute(AdPointNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setPoint(notification.getPoint().toString());
        param.setUserNum(notification.getUserNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.MERCHANT_RED_SWEEP);
        param.setLoveTypeEnum(LoveTypeEnum.RED_PACKAGE);
        param.setTempBizId(notification.getAdId() == null ? "0" : notification.getAdId().toString());
        param.setTitle(MemberTransactionTypeEnum.MERCHANT_RED_SWEEP.getName());
        param.setTransactionDesc(MemberTransactionTypeEnum.MERCHANT_RED_SWEEP.getDescPrefix() + notification.getTitle());
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderBalanceIncome(param);
    }
}
