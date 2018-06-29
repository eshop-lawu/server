package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.user.MerchantFansNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 商家邀请粉丝退还积分 - 从事务
 * @author meishuquan
 * @date 2017/9/21
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_MERCHANT_FANS)
public class MerchantFansTransactionFollowServiceImpl extends AbstractTransactionFollowService<MerchantFansNotification, Reply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Override
    public void execute(MerchantFansNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setPoint(notification.getPoint().toString());
        param.setUserNum(notification.getUserNum());
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.AD_RETURN_POINT);
        param.setBizId(notification.getBizId() == null ? "" : "inviteOverDue_"+notification.getBizId().toString());
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderAddPoint(param);
    }
}
