package com.lawu.eshop.order.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderPaymentStatusReply;
import com.lawu.eshop.mq.dto.property.ShoppingOrderPaymentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 支付购物订单事务处理-从模块
 * 
 * @author Sunny
 * @date 2017年4月14日
 */
@Service("shoppingOrderPaymentTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_PAY_SHOPPING_ORDER)
public class ShoppingOrderPaymentTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingOrderPaymentNotification, ShoppingOrderPaymentStatusReply> {

	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	/**
	 * 接收资产模块支付购物订单时发送的消息
	 */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(ShoppingOrderPaymentNotification notification) {
    	shoppingOrderService.paymentSuccessful(notification);
    }
    
    @Override
    public ShoppingOrderPaymentStatusReply getReply(ShoppingOrderPaymentNotification notification) {
        return shoppingOrderService.paymentStatus(notification);
    }
}