package com.lawu.eshop.property.constants;

public enum TransactionTitleEnum {

	CASH("提现"),
	CASH_FAIL_BACK("提现失败"),
	ORDER_PAY("购物"),
	ORDER_PAY_REFUND("退款"),
	PAY("买单"),
	INTEGRAL_RECHARGE("积分充值"),
	INVITE_FANS("邀请粉丝"),
	ADD_AD("投放广告"),
	ADD_RED_PACKET("发红包"),
	AD_RETURN_POINT("积分退还"),
	USER_GET_REDPACKET("红包"),
	CLICK_AD("看广告"),
	AD_COMMISSION("广告提成"),
	RECHARGE("余额充值"),
	DEPOSIT("缴纳保证金"),
	DEPOSIT_REFUND("保证金退款"),
	BACKAGE("平台充值"),
	MEMBER_RED_PACKET("发红包");
	
	private String val;

	TransactionTitleEnum(String val) {
		this.val = val;
	}

	public static TransactionTitleEnum getEnum(String val) {
		TransactionTitleEnum[] values = TransactionTitleEnum.values();
		for (TransactionTitleEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public String getVal() {
		return val;
	}
	
}
