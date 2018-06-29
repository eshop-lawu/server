package com.lawu.eshop.property.param;

import java.util.Date;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * 商家 E咻 & 红包 下架后退款原路退
 */
public class MerchantAdRefundDataParam {

	private String adId;

	private String refundMoney;

	private String userNum;

	private TransactionPayTypeEnum transactionPayTypeEnum;

	private String tradeNo;

	/**
	 * 1-app、2-pc
	 */
	private Byte clientType;

	//主事务执行时间
	private Date gmtExecute;

	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
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

	public String getAdId() {

		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Byte getClientType() {
		return clientType;
	}

	public void setClientType(Byte clientType) {
		this.clientType = clientType;
	}

	public Date getGmtExecute() {
		return gmtExecute;
	}

	public void setGmtExecute(Date gmtExecute) {
		this.gmtExecute = gmtExecute;
	}
}
