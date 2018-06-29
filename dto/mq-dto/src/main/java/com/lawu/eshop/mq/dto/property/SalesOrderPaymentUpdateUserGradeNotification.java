package com.lawu.eshop.mq.dto.property;

import java.math.BigDecimal;

import com.lawu.compensating.transaction.Notification;

/**
 * 支付购物订单/买单成功后修改用户等级，发送给用户模块的数据
 * 
 * @author yangqh
 * @date 2017年11月24日
 */
public class SalesOrderPaymentUpdateUserGradeNotification extends Notification {


	/**
	 * 订单ID
	 */
	private String orderId;

	/**
	 * 交易记录ID
	 */
	private Long transactionDetailId;
	
	/**
	 * 支付金额
	 */
	private BigDecimal payMoney;

	/**
	 * 用户编号
	 */
	private String userNum;

	/**
	 * 交易类型（6-买单|7-购物）
	 */
	private Byte transactionType;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getTransactionDetailId() {
		return transactionDetailId;
	}

	public void setTransactionDetailId(Long transactionDetailId) {
		this.transactionDetailId = transactionDetailId;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Byte getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Byte transactionType) {
		this.transactionType = transactionType;
	}
}
