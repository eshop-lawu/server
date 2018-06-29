package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.property.SalesOrderPaymentUpdateUserGradeNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.srv.constans.TransactionConstant;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 确认收货7天后或待收货14天后定时打款给商家后修改用户等级事务处理-主模块
 *
 * @author yangqh
 * @date 2017年11月24日
 */
@Service("shoppingOrderPaymentUpdateUserGradeTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.SHOPPING_ORDER_PAY_UPDATE_USER_GRADE, topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_SHOPPING_ORDER_PAY_UPDATE_USER_GRADE)
public class ShoppingOrderPaymentUpdateUserGradeTransactionMainServiceImpl extends AbstractTransactionMainService<SalesOrderPaymentUpdateUserGradeNotification, Reply> {

    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;

    @Override
    public SalesOrderPaymentUpdateUserGradeNotification selectNotification(Long transactionDetailId) {

        TransactionDetailDO transactionDetailDO = transactionDetailDOMapper.selectByPrimaryKey(transactionDetailId);

        SalesOrderPaymentUpdateUserGradeNotification notification = new SalesOrderPaymentUpdateUserGradeNotification();
        notification.setOrderId(transactionDetailDO.getBizId());
        notification.setTransactionDetailId(transactionDetailId);
        notification.setPayMoney(transactionDetailDO.getAmount());
        notification.setUserNum(transactionDetailDO.getRemark());
        notification.setTransactionType(transactionDetailDO.getTransactionType());
        return notification;
    }
}
