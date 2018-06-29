package com.lawu.eshop.mall.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mall.srv.constants.TransactionConstant;
import com.lawu.eshop.mall.srv.service.CommentMerchantService;
import com.lawu.eshop.mq.dto.mall.CommentMerchantNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * @author zhangyong
 * @date 2017/4/12.
 */
@Service("orderCommentMerchantTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.COMMENT_MERCHANT, topic = MqConstant.TOPIC_MALL_SRV, tags = MqConstant.TAG_COMMENT_MERCHANT)
public class OrderCommentMerchantTransactionMainServiceImpl extends AbstractTransactionMainService<CommentMerchantNotification, Reply> {

    @Autowired
    private CommentMerchantService commentMerchantService;

    @Override
    public CommentMerchantNotification selectNotification(Long payOrderId) {
        CommentMerchantNotification notification = new CommentMerchantNotification();
        notification.setPayOrderId(payOrderId);
        notification.setCommentsCount(commentMerchantService.countCommentMerchantByPayOrderId(payOrderId));
        return notification;
    }
}
