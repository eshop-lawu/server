package com.lawu.eshop.mall.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mall.srv.service.CommentProductService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderAutoCommentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 商品订单自动好评事务-从模块
 * 
 * @author Sunny
 * @date 2017年4月15日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_AUTO_COMMENT)
public class ShoppingOrderAutoCommentTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingOrderAutoCommentNotification, Reply> {

	@Autowired
	private CommentProductService commentProductService;
	
    @Override
    public void execute(ShoppingOrderAutoCommentNotification notification) {
		commentProductService.saveCommentProductInfoOrderJob(notification);
    }
}
