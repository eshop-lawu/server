package com.lawu.eshop.user.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCreateOrderFansNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.srv.service.FansMerchantService;

/**
 * 创建购物订单事务
 * 用户成为商家粉丝-主模块
 * 
 * @author Sunny
 * @date 2017年5月3日
 */
@Service("shoppingOrderCreateOrderFansTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CREATE_ORDER_FANS)
public class ShoppingOrderCreateOrderFansTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingOrderCreateOrderFansNotification, Reply> {

	@Autowired
	private FansMerchantService fansMerchantService;
	
	/**
	 * 接收订单模块创建订单发送的消息
	 * 用户成为商家的粉丝
	 */
    @Transactional(rollbackFor = Exception.class)
    @Override
	public void execute(ShoppingOrderCreateOrderFansNotification notification) {
		fansMerchantService.saveFansMerchant(notification.getMerchantId(), notification.getMemberId(), FansMerchantChannelEnum.ORDER_PAY);
	}
}