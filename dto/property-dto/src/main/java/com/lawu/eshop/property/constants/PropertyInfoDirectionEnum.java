package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 积分、余额明细走向
 * </p>
 * @author Yangqh
 * @date 2017年4月14日 下午4:54:33
 *
 */
public enum PropertyInfoDirectionEnum {
	OUT((byte) 0x01),
	IN((byte) 0x02);
	private Byte val;

	PropertyInfoDirectionEnum(Byte val) {
		this.val = val;
	}

	public static PropertyInfoDirectionEnum getEnum(Byte val) {
		PropertyInfoDirectionEnum[] values = PropertyInfoDirectionEnum.values();
		for (PropertyInfoDirectionEnum object : values) {
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
