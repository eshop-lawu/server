package com.lawu.eshop.mall.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mall.srv.service.CommentProductService;
import com.lawu.eshop.mq.dto.product.DelProductCommentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 
 * <p>
 * Description: 删除商品型号时，发送消息删除商品型号下的评价
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月18日 下午12:55:22
 *
 */
@Service("delProductCommentTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PRODUCT_SRV, tags = MqConstant.TAG_DEL_COMMENT)
public class DelProductCommentTransactionFollowServiceImpl extends AbstractTransactionFollowService<DelProductCommentNotification, Reply> {

	@Autowired
	private CommentProductService commentProductService;

	@Override
	public void execute(DelProductCommentNotification notification) {
		Long productModelId = notification.getProductModelId();
		commentProductService.delCommentByProductModelId(productModelId);
	}

}
