package com.lawu.eshop.property.srv.service.impl.transaction;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.user.MemberFansNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;
import com.lawu.eshop.property.srv.service.PropertyService;

/**
 * @author meishuquan
 * @date 2017/9/21
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_MEMBER_FANS)
public class MemberFansTransactionFollowServiceImpl extends AbstractTransactionFollowService<MemberFansNotification, Reply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PropertyService propertyService;

    @Override
    public void execute(MemberFansNotification notification) {
        String pointRate = propertyService.getValue(PropertyType.MEMBER_FANS_POINT);
        String point = StringUtils.isEmpty(pointRate) ? "0.8" : String.valueOf(1 * Double.valueOf(pointRate));
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setPoint(point);
        param.setUserNum(notification.getUserNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.MERCHANT_RED_SWEEP);
        param.setLoveTypeEnum(LoveTypeEnum.MEMBER_FANS);
        param.setTempBizId("0");
        param.setTransactionDesc(MemberTransactionTypeEnum.MERCHANT_RED_SWEEP.getDescPrefix()+notification.getMerchantStoreName());
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderBalanceIncome(param);
    }
}
