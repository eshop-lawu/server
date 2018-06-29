package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 第三方支付状态枚举
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午9:15:55
 *
 */
public enum ThirdPayStatusEnum {
	PAYING((byte) 0x01,"待支付"), // 待支付
	SUCCESS((byte) 0x02,"成功"), // 成功
	FAILURE((byte) 0x03,"失败"); // 失败
	private Byte val;
	private String name;

	ThirdPayStatusEnum(Byte val,String name) {
		this.val = val;
		this.name = name;
	}

	public static ThirdPayStatusEnum getEnum(Byte val) {
		ThirdPayStatusEnum[] values = ThirdPayStatusEnum.values();
		for (ThirdPayStatusEnum object : values) {
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
