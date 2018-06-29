package com.lawu.eshop.property.srv.service.impl.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderPaymentStatusReply;
import com.lawu.eshop.mq.dto.property.ShoppingOrderPaymentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.param.OrderAutoRefundConcurrentDataParam;
import com.lawu.eshop.property.srv.constans.TransactionConstant;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.service.OrderService;

/**
 * 支付购物订单事务处理-主模块
 * 
 * @author Sunny
 * @date 2017年4月14日
 */
@Service("shoppingOrderPaymentTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.PAY_SHOPPING_ORDER, topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_PAY_SHOPPING_ORDER)
public class ShoppingOrderPaymentTransactionMainServiceImpl extends AbstractTransactionMainService<ShoppingOrderPaymentNotification, ShoppingOrderPaymentStatusReply> {
    
    private static final Logger log = LoggerFactory.getLogger(ShoppingOrderPaymentTransactionMainServiceImpl.class);
    
	@Autowired
	private TransactionDetailDOMapper transactionDetailDOMapper;
	
	@Autowired
	private OrderService orderService;
	
	@Override
    public ShoppingOrderPaymentNotification selectNotification(Long transactionDetailId) {
		TransactionDetailDO transactionDetailDO = transactionDetailDOMapper.selectByPrimaryKey(transactionDetailId);
    	ShoppingOrderPaymentNotification notification = new ShoppingOrderPaymentNotification();
        notification.setShoppingOrderIds(transactionDetailDO.getBizId());
        notification.setThirdNumber(transactionDetailDO.getThirdTransactionNum());
        notification.setPaymentMethod(TransactionPayTypeEnum.getEnum(transactionDetailDO.getTransactionAccountType()));
        return notification;
    }
	
	@Override
	public void afterSuccess(Long relateId, ShoppingOrderPaymentStatusReply reply) {
	    if (reply.getShoppingOrderIds() == null) {
	        return;
	    }
	    OrderAutoRefundConcurrentDataParam param = new OrderAutoRefundConcurrentDataParam();
	    param.setBizId(reply.getShoppingOrderIds());
	    param.setTradeNo(reply.getThirdNumber());
	    param.setTransactionPayTypeEnum(reply.getPaymentMethod());
	    param.setUserNum(reply.getMemberNum());
	    try {
	        orderService.autoRefundConcurrent(param);
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	        throw new RuntimeException(e);
	    }
	}
}
