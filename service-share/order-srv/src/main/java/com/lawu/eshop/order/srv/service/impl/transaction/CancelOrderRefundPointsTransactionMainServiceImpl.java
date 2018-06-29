package com.lawu.eshop.order.srv.service.impl.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.CancelOrderRefundPointsNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 取消购物订单退还积分事务-主模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@Service("cancelOrderRefundPointsTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.CANCEL_ORDER_REFUND_POINTS, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CANCEL_ORDER_REFUND_POINTS)
public class CancelOrderRefundPointsTransactionMainServiceImpl extends
        AbstractTransactionMainService<CancelOrderRefundPointsNotification, Reply> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;

    @Override
    public CancelOrderRefundPointsNotification selectNotification(Long shoppingOrderId) {
        CancelOrderRefundPointsNotification notification = new CancelOrderRefundPointsNotification();
        ShoppingOrderBO shoppingOrderBO = shoppingOrderService.getShoppingOrder(shoppingOrderId);
        notification.setShoppingOrderId(shoppingOrderId);
        notification.setPoint(shoppingOrderBO.getDeductionPoints());
        notification.setMemberNum(shoppingOrderBO.getMemberNum());
        notification.setGmtExecute(new Date());
        return notification;
    }
}
