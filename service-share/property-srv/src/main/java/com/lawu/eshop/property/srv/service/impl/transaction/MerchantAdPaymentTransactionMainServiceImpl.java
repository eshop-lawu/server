package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.property.AdPaymentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.srv.constans.TransactionConstant;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商家发广告异步回调后修改广告记录-主事务
 */
@Service("merchantAdPaymentTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.HANDLE_MERCHANT_AD, topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_AD)
public class MerchantAdPaymentTransactionMainServiceImpl extends AbstractTransactionMainService<AdPaymentNotification, Reply> {

    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;

    @Override
    public AdPaymentNotification selectNotification(Long transactionDetailId) {

        TransactionDetailDO transactionDetailDO = transactionDetailDOMapper.selectByPrimaryKey(transactionDetailId);
        AdPaymentNotification notification = new AdPaymentNotification();

        notification.setAdId(transactionDetailDO.getBizId());
        notification.setThirdNumber(transactionDetailDO.getThirdTransactionNum());
        notification.setPaymentMethod(transactionDetailDO.getTransactionAccountType());

        return notification;
    }
}
