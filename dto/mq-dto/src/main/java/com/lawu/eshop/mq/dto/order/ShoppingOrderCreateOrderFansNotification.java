package com.lawu.eshop.mq.dto.order;

import com.lawu.compensating.transaction.Notification;

/**
 * 
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingOrderCreateOrderFansNotification extends Notification {
	
	private static final long serialVersionUID = 579024029688970787L;

	/**
	 * 用户id
	 */
	private Long memberId;
	
	/**
	 * 商家id
	 */
	private Long merchantId;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
}
