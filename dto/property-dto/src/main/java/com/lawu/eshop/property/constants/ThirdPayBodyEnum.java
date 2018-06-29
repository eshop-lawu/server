package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description:  第三方支付主题
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午4:46:15
 *
 */
public enum ThirdPayBodyEnum {
	
	B_RECHARGE_BALANCE_I("商家充值余额I"),
	B_RECHARGE_BALANCE_A("商家充值余额A"),
	B_RECHARGE_BALANCE_P("商家充值余额P"),
	B_RECHARGE_POINT_I("商家充值积分I"),
	B_RECHARGE_POINT_A("商家充值积分A"),
	B_RECHARGE_POINT_P("商家充值积分P"),
	B_PAY_BOND_I("缴纳保证金I"),
	B_PAY_BOND_A("缴纳保证金A"),
	B_PAY_BOND_P("缴纳保证金P"),
	M_RECHARGE_BALANCE_I("用户充值余额I"),
	M_RECHARGE_BALANCE_A("用户充值余额A"),
	M_RECHARGE_POINT_I("用户充值积分I"),
	M_RECHARGE_POINT_A("用户充值积分A"),
	ORDER_PAY_I("订单付款I"),
	ORDER_PAY_A("订单付款A"),
	BILL_PAY_I("买单I"),
	BILL_PAY_A("买单A"),
	M_RED_PACKET_I("发红包I"),
	M_RED_PACKET_A("发红包A"),
	B_AD_I("发广告I"),
	B_AD_A("发广告A"),
	B_AD_P("发广告P");
	
	private String val;

	ThirdPayBodyEnum(String val) {
		this.val = val;
	}

	public static ThirdPayBodyEnum getEnum(String val) {
		ThirdPayBodyEnum[] values = ThirdPayBodyEnum.values();
		for (ThirdPayBodyEnum object : values) {
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
