package com.lawu.eshop.mq.dto.order.reply;

import com.lawu.compensating.transaction.Reply;
import com.lawu.eshop.mq.dto.product.CheckLessInventoryResultEnum;

/**
 * 创建订单事务回复消息
 * 
 * @author Sunny
 * @date 2017年6月6日
 */
public class ShoppingOrderCreateOrderReply extends Reply {

	private static final long serialVersionUID = -5142282166586603538L;
	
	private CheckLessInventoryResultEnum result;

	public CheckLessInventoryResultEnum getResult() {
		return result;
	}

	public void setResult(CheckLessInventoryResultEnum result) {
		this.result = result;
	}
	
}
