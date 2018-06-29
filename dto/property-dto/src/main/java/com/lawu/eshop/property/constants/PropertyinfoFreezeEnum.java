package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 资产表property-info表freeze状态枚举
 * </p>
 * @author Yangqh
 * @date 2017年5月16日 下午4:36:51
 *
 */
public enum PropertyinfoFreezeEnum {

	NO((byte) 0x00), //否
	YES((byte) 0x01);//是
	
	private Byte val;

	PropertyinfoFreezeEnum(Byte val) {
		this.val = val;
	}

	public static PropertyinfoFreezeEnum getEnum(Byte val) {
		PropertyinfoFreezeEnum[] values = PropertyinfoFreezeEnum.values();
		for (PropertyinfoFreezeEnum object : values) {
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
