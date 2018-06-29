package com.lawu.eshop.property.constants;

/**
 * 会员余额交易明细大类
 */
public enum MemberTransactionCategoryEnum {

	ALL((byte) 0x01, "全部分类"),
	RED_SWEEP((byte) 0x02, "红包"),
	RECOMMEND_INCOME((byte) 0x03, "推荐"),
	PREFERRED_RED_SWEEP((byte) 0x04, "优选红包"),
	WITHDRAW((byte) 0x05, "提现"),
//	PAY((byte) 0x06, "买单"),
	REFUND_MONEY((byte) 0x07, "退款"),
	RECHARGE((byte) 0x08, "充值"),
	SHOPPING((byte) 0x09, "购物");


	private Byte value;
	private String name;

	MemberTransactionCategoryEnum(Byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public Byte getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static MemberTransactionCategoryEnum getEnum(Byte val) {
		MemberTransactionCategoryEnum[] values = MemberTransactionCategoryEnum.values();
		for (MemberTransactionCategoryEnum object : values) {
			if (object.getValue().equals(val)) {
				return object;
			}
		}
		return null;
	}
}