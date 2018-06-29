package com.lawu.eshop.product.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCancelOrderNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.product.srv.service.ProductModelService;

/**
 * 
 * @author Sunny
 * @date 2017/04/06
 */
@Service("shoppingOrderCancelOrderTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CANCEL_ORDER)
public class ShoppingOrderCancelOrderTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingOrderCancelOrderNotification, Reply> {

	@Autowired
	private ProductModelService productModelService;

	/**
	 * 释放库存
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(ShoppingOrderCancelOrderNotification shoppingOrderCancelOrderNotification) {
		// 释放商品库存，以及在商品型号库存表添加记录
		productModelService.releaseInventory(shoppingOrderCancelOrderNotification);
	}
}
