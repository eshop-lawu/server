package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 充值类型
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午8:45:23
 *
 */
public enum PayTypeEnum {
	BALANCE((byte) 0x01,"余额"), // 余额
	POINT((byte) 0x02,"积分"); // 积分
	private Byte val;
	private String name;

	PayTypeEnum(Byte val,String name) {
		this.val = val;
		this.name = name;
	}

	public static PayTypeEnum getEnum(Byte val) {
		PayTypeEnum[] values = PayTypeEnum.values();
		for (PayTypeEnum object : values) {
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
