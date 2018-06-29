package com.lawu.eshop.pay;

public class ThirdPayRefundParam {
	//第三方平台订单号
	private String tradeNo;
	//订单总额
	private String totalMoney;
	//退款金额
	private String refundMoney;
	//退款单号（单次退款单号不能变）
	private String refundId;
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
}
