package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月5日 下午7:16:54
 *
 */
public enum CashChannelEnum {
	ARTIFICIAL((byte) 0x01), // 人工
	DPAY((byte) 0x02); // 代付
	private Byte val;

	CashChannelEnum(Byte val) {
		this.val = val;
	}

	public static CashChannelEnum getEnum(Byte val) {
		CashChannelEnum[] values = CashChannelEnum.values();
		for (CashChannelEnum object : values) {
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
