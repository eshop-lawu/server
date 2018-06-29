package com.lawu.eshop.mq.dto.property;

import com.lawu.compensating.transaction.Notification;

/**
 * 资产模块支付广告成功，发送给广告模块的数据
 */
public class AdPaymentNotification extends Notification {


	private static final long serialVersionUID = -695609766974311497L;

	private String adId;
	
	/**
	 * 支付方式(1-余额 2-微信 3-支付宝)
	 */
	private Byte paymentMethod;

	/**
	 * 第三方支付交易号
	 */
	private String thirdNumber;

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
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
