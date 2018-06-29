package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.PayOrderNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.srv.constans.TransactionConstant;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 买单时支付成功修改买单状态 - 主事务
 * @author zhangyong
 * @date 2017/4/13.
 */
@Service("payOrderTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.PAYORDER, topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_PAYORDER)
public class PayOrderTransactionMainServiceImpl extends AbstractTransactionMainService<PayOrderNotification, Reply> {
    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;
    @Override
    public PayOrderNotification selectNotification(Long transcationDetailId) {
        TransactionDetailDO transactionDetailDO = transactionDetailDOMapper.selectByPrimaryKey(transcationDetailId);
        PayOrderNotification notification = new PayOrderNotification();
        notification.setPayOrderId(transactionDetailDO.getBizId() == null ? 0 : Long.valueOf(transactionDetailDO.getBizId()));
        notification.setThirdNum(transactionDetailDO.getThirdTransactionNum());
        notification.setPayType(transactionDetailDO.getTransactionAccountType());
        return notification;
    }
}
