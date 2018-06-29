package com.lawu.eshop.mall.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mall.srv.constants.TransactionConstant;
import com.lawu.eshop.mq.dto.mall.CommentProductNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

import org.springframework.stereotype.Service;

//import com.lawu.eshop.mall.srv.bo.CommentProductNotification;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */


@Service("orderCommentProductTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.COMMENT_PRODUCT, topic = MqConstant.TOPIC_MALL_SRV, tags = MqConstant.TAG_COMMENT_PRODUCT)
public class OrderCommentProductTransactionMainServiceImpl extends AbstractTransactionMainService<CommentProductNotification, Reply> {

    @Override
    public CommentProductNotification selectNotification(Long shoppingOrderItemId) {
        CommentProductNotification regNotification = new CommentProductNotification();
        regNotification.setShoppingOrderItemId(shoppingOrderItemId);
        return regNotification;
    }
}
