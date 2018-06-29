package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 重复回调到业务层判断已支付时，自动退款给用户
 */
public class OrderAutoRefundConcurrentDataParam extends OrderRefundParam {

	// 用户编号
	@NotBlank(message = "userNum不能为空")
	private String userNum;

	//业务主键（订单退款是订单项ID）
	@NotBlank(message = "bizId不能为空")
	private String bizId;

	// 支付方式
	@NotNull(message = "transactionPayTypeEnum不能为空")
	private TransactionPayTypeEnum transactionPayTypeEnum;

	//第三方订单号
	@NotBlank(message = "tradeNo不能为空")
	private String tradeNo;

	private String orderItemProdcutName = "重复支付退款";

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public TransactionPayTypeEnum getTransactionPayTypeEnum() {
		return transactionPayTypeEnum;
	}

	public void setTransactionPayTypeEnum(TransactionPayTypeEnum transactionPayTypeEnum) {
		this.transactionPayTypeEnum = transactionPayTypeEnum;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderItemProdcutName() {
		return orderItemProdcutName;
	}

	public void setOrderItemProdcutName(String orderItemProdcutName) {
		this.orderItemProdcutName = orderItemProdcutName;
	}
}
