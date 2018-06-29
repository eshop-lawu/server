package com.lawu.eshop.product.srv.service.impl.transaction;

import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.product.DelProductCommentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.product.srv.constants.TransactionConstant;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月18日 下午12:47:22
 *
 */
@Service("delProductCommentTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.DEL_COMMENT, topic = MqConstant.TOPIC_PRODUCT_SRV, tags = MqConstant.TAG_DEL_COMMENT)
public class DelProductCommentTransactionMainServiceImpl extends AbstractTransactionMainService<DelProductCommentNotification, Reply> {
    @Override
    public DelProductCommentNotification selectNotification(Long productModelId) {
    	DelProductCommentNotification notification = new DelProductCommentNotification();
        notification.setProductModelId(productModelId);
        return notification;
    }
}
