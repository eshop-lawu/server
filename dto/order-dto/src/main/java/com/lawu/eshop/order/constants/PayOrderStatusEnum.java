package com.lawu.eshop.order.constants;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public enum PayOrderStatusEnum {
	STATUS_UNPAY((byte) 0x01), // 1-待支付
	STATUS_PAY_SUCCESS((byte) 0x02), // 2-成功
	STATUS_PAY_fAIL((byte) 0x03);// 3-失败
	
	private Byte val;

	PayOrderStatusEnum(Byte val) {
		this.val = val;
	}

	public Byte getVal() {
		return val;
	}

	public static PayOrderStatusEnum getEnum(Byte val) {
		PayOrderStatusEnum[] values = PayOrderStatusEnum.values();
		for (PayOrderStatusEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}
}
