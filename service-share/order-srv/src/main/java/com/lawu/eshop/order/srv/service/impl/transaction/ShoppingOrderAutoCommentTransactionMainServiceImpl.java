package com.lawu.eshop.order.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderAutoCommentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderItemService;

/**
 * 商品订单自动好评事务-主模块
 * 
 * @author Sunny
 * @date 2017/04/14
 */
@Service("shoppingOrderAutoCommentTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.AUTO_COMMENT, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_AUTO_COMMENT)
public class ShoppingOrderAutoCommentTransactionMainServiceImpl extends AbstractTransactionMainService<ShoppingOrderAutoCommentNotification, Reply> {
	
	@Autowired
	private ShoppingOrderItemService shoppingOrderItemService;
	
	
	/**
	 * 组装其他模块发送的数组
	 */
    @Override
    public ShoppingOrderAutoCommentNotification selectNotification(Long shoppingOrderItemId) {
    	ShoppingOrderAutoCommentNotification rtn = new ShoppingOrderAutoCommentNotification();
    	ShoppingOrderItemExtendBO shoppingOrderItemExtendBO = shoppingOrderItemService.getByComment(shoppingOrderItemId);
    	rtn.setMemberId(shoppingOrderItemExtendBO.getShoppingOrder().getMemberId());
    	rtn.setMerchantId(shoppingOrderItemExtendBO.getShoppingOrder().getMerchantId());
    	rtn.setProductId(shoppingOrderItemExtendBO.getProductId());
    	rtn.setShoppingOrderItem(shoppingOrderItemExtendBO.getId());
    	rtn.setProductModelId(shoppingOrderItemExtendBO.getProductModelId());
        return rtn;
    }
}
