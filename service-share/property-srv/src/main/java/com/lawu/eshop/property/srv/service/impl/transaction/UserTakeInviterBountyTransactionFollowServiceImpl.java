package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mq.dto.ad.UserTakeInviterBountyNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 领取邀请奖励金 - 从事务
 *
 * @author zhangrc
 * @date 2017/12/21
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_TAKE_INVITER_BOUNTY)
public class UserTakeInviterBountyTransactionFollowServiceImpl extends AbstractTransactionFollowService<UserTakeInviterBountyNotification, Reply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Override
    public void execute(UserTakeInviterBountyNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setPoint(notification.getMoney().toString());
        param.setUserNum(notification.getUserNum());
        if (notification.getUserType() == UserTypeEnum.MEMBER) {
            param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.PLATFORM_RED_SWEEP_REG);
            param.setTransactionDesc(MemberTransactionTypeEnum.PLATFORM_RED_SWEEP_REG.getDescPrefix());
        } else {
            param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.PLATFORM_RED_SWEEP_REG);
            param.setTransactionDesc(MerchantTransactionTypeEnum.PLATFORM_RED_SWEEP_REG.getDescPrefix());
        }

        param.setLoveTypeEnum(LoveTypeEnum.RED_PACKAGE);
        param.setTempBizId(notification.getId() == null ? "0" : notification.getId().toString());
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderBalanceIncome(param);
    }
}
