package com.lawu.eshop.order.srv.service.impl.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.CreateOrderDeductionPointsNotification;
import com.lawu.eshop.mq.dto.order.reply.CreateOrderDeductionPointsReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 创建购物订单扣除积分事务-主模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@Service("createOrderDeductionPointsTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.CREATE_ORDER_DEDUCTION_POINTS, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CREATE_ORDER_DEDUCTION_POINTS)
public class CreateOrderDeductionPointsTransactionMainServiceImpl extends
        AbstractTransactionMainService<CreateOrderDeductionPointsNotification, CreateOrderDeductionPointsReply> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;

    @Override
    public CreateOrderDeductionPointsNotification selectNotification(Long shoppingOrderId) {
        CreateOrderDeductionPointsNotification notification = new CreateOrderDeductionPointsNotification();
        ShoppingOrderBO shoppingOrderBO = shoppingOrderService.getShoppingOrder(shoppingOrderId);
        notification.setShoppingOrderId(shoppingOrderId);
        notification.setPoint(shoppingOrderBO.getDeductionPoints());
        notification.setMemberNum(shoppingOrderBO.getMemberNum());
        notification.setGmtExecute(new Date());
        return notification;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void afterSuccess(Long shoppingOrderId, CreateOrderDeductionPointsReply reply) {
        shoppingOrderService.deductionPoints(shoppingOrderId, reply);
    }
}
