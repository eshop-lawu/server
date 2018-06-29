package com.lawu.eshop.property.constants;

/**
 *
 */
public enum FreezeBizTypeEnum {

	PRODUCT_ORDER_COMFIRM_DELIVERY((byte) 0x0A), // (订单)用户确认收货初始化冻结资金
	PRODUCT_ORDER_MERCHANT_DOREFUND((byte) 0x0B);//(订单)商家同意退款减冻结资金

	private Byte val;

	FreezeBizTypeEnum(Byte val) {
		this.val = val;
	}

	public static FreezeBizTypeEnum getEnum(Byte val) {
		FreezeBizTypeEnum[] values = FreezeBizTypeEnum.values();
		for (FreezeBizTypeEnum object : values) {
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
