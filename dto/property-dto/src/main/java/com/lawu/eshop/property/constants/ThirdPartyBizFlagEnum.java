package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 第三方支付对应业务类型
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月6日 下午7:29:10
 *
 */
public enum ThirdPartyBizFlagEnum {
	BUSINESS_PAY_BALANCE((byte) 0x01), // 商家充值余额
	BUSINESS_PAY_POINT((byte) 0x02), // 商家充值积分
	BUSINESS_PAY_BOND((byte) 0x03), // 缴纳保证金
	MEMBER_PAY_BALANCE((byte) 0x04), // 用户充值余额
	MEMBER_PAY_POINT((byte) 0x05), // 用户充值积分
	MEMBER_PAY_ORDER((byte) 0x06), // 订单付款
	MEMBER_PAY_BILL((byte) 0x07),// 买单
	MEMBER_RED_PACKET((byte) 0x08),//用户发红包
	BUSINESS_ADD_AD((byte) 0x09);// 商家发广告（E咻&红包）
	private Byte val;

	ThirdPartyBizFlagEnum(Byte val) {
		this.val = val;
	}

	public static ThirdPartyBizFlagEnum getEnum(Byte val) {
		ThirdPartyBizFlagEnum[] values = ThirdPartyBizFlagEnum.values();
		for (ThirdPartyBizFlagEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public Byte getVal() {
		return val;
	}
	
}
