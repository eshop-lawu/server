package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.property.MemberRedPacketPaymentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.srv.constans.TransactionConstant;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户发红包修改红包状态-主事务
 */
@Service("memberRedPacketPaymentTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.HANDLE_MEMBER_RED_PACKET, topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_MEMBER_RED_PACKET)
public class MemberRedPacketPaymentTransactionMainServiceImpl extends AbstractTransactionMainService<MemberRedPacketPaymentNotification, Reply> {

    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;

    @Override
    public MemberRedPacketPaymentNotification selectNotification(Long transactionDetailId) {

        TransactionDetailDO transactionDetailDO = transactionDetailDOMapper.selectByPrimaryKey(transactionDetailId);

        MemberRedPacketPaymentNotification notification = new MemberRedPacketPaymentNotification();

        notification.setRedPacketId(transactionDetailDO.getBizId().toString());
        notification.setThirdNumber(transactionDetailDO.getThirdTransactionNum());
        notification.setPaymentMethod(transactionDetailDO.getTransactionAccountType());

        return notification;
    }
}
