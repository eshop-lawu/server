package com.lawu.eshop.ad.srv.service.impl.transaction;

import javax.annotation.Resource;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.ad.constants.AdPayTypeEnum;
import com.lawu.eshop.ad.param.AdSetPayParam;
import com.lawu.eshop.ad.param.UserRedPacketUpdateParam;
import com.lawu.eshop.ad.srv.service.AdService;
import com.lawu.eshop.ad.srv.service.UserRedPacketService;
import com.lawu.eshop.mq.dto.property.AdPaymentNotification;
import com.lawu.eshop.mq.dto.property.MemberRedPacketPaymentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商家发广告异步回调后修改广告记录-从事务
 */
@Service("merchantAdPaymentTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_AD)
public class MerchantAdPaymentTransactionFollowServiceImpl extends AbstractTransactionFollowService<AdPaymentNotification, Reply> {

    @Autowired
    private AdService adService;

    /**
     * 接收资产模块支付广告时发送的消息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(AdPaymentNotification notification) {
        AdSetPayParam param = new AdSetPayParam();
        param.setId(Long.parseLong(notification.getAdId()));
        param.setPayTypeEnum(AdPayTypeEnum.getEnum(notification.getPaymentMethod()));
        param.setThirdNumber(notification.getThirdNumber());
        adService.updateAdIsPay(param);
    }
}