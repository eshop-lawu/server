package com.lawu.eshop.mq.dto.property;

import com.lawu.compensating.transaction.Notification;

/**
 * 用户发红包支付成功后修改红包状态
 */
public class MemberRedPacketPaymentNotification extends Notification {

	private static final long serialVersionUID = 9149579713862090156L;

	/**
	 * 红包ID
	 */
	private String redPacketId;
	
	/**
	 * 支付方式(1-余额 2-微信 3-支付宝)
	 */
	private Byte paymentMethod;

	/**
	 * 第三方支付交易号
	 */
	private String thirdNumber;

	public String getRedPacketId() {
		return redPacketId;
	}

	public void setRedPacketId(String redPacketId) {
		this.redPacketId = redPacketId;
	}

	public Byte getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Byte paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(String thirdNumber) {
		this.thirdNumber = thirdNumber;
	}
}
