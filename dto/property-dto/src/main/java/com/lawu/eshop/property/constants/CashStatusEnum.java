package com.lawu.eshop.property.constants;

/**
 * @author Sunny
 * @date 2017/3/30
 */
public enum CashStatusEnum {
	ALL((byte) 0x00, "全部"),APPLY((byte) 0x01, "申请中"), ACCEPT((byte) 0x02, "受理中"), SUCCESS((byte) 0x03, "成功"), FAILURE((byte) 0x04, "失败");
	private Byte val;
	private String name;

	CashStatusEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}

	public static CashStatusEnum getEnum(Byte val) {
		CashStatusEnum[] values = CashStatusEnum.values();
		for (CashStatusEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public Byte getVal() {
		return val;
	}

	public String getName() {
		return name;
	}

}
