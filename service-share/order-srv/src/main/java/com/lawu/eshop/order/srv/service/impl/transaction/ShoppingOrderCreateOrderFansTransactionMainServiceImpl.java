package com.lawu.eshop.order.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCreateOrderFansNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 创建购物订单事务
 * 用户成为商家粉丝-主模块
 * 
 * @author Sunny
 * @date 2017/05/03
 */
@Service("shoppingOrderCreateOrderFansTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.CREATE_ORDER_FANS, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CREATE_ORDER_FANS)
public class ShoppingOrderCreateOrderFansTransactionMainServiceImpl extends AbstractTransactionMainService<ShoppingOrderCreateOrderFansNotification, Reply> {
	
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	/**
	 * 组装其他模块发送的数组
	 */
    @Override
    public ShoppingOrderCreateOrderFansNotification selectNotification(Long shoppingOrderId) {
    	ShoppingOrderCreateOrderFansNotification rtn = new ShoppingOrderCreateOrderFansNotification();
        // 获取购物订单以及订单项
    	ShoppingOrderBO shoppingOrderBO = shoppingOrderService.getShoppingOrder(shoppingOrderId);
        
    	rtn.setMemberId(shoppingOrderBO.getMemberId());
    	rtn.setMerchantId(shoppingOrderBO.getMerchantId());
    	
        return rtn;
    }
}
