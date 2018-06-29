package com.lawu.eshop.mall.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mall.srv.service.CommentProductService;
import com.lawu.eshop.mq.dto.order.ShoppingRefundAgreeToRefundDeleteCommentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 商家同意退款事务-从模块 删除商品订单评论
 * 
 * @author Sunny
 * @date 2017/04/18
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_AGREE_TO_REFUND_DELETE_COMMENT)
public class ShoppingRefundAgreeToRefundDeleteCommentTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingRefundAgreeToRefundDeleteCommentNotification, Reply> {

	@Autowired
	private CommentProductService commentProductService;

	@Override
	public void execute(ShoppingRefundAgreeToRefundDeleteCommentNotification notification) {
		// 删除商品评价
		commentProductService.delCommentByOrderItemId(notification.getShoppingOrderItemId());
	}
}
