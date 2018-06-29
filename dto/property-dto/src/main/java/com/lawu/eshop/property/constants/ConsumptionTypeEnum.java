package com.lawu.eshop.property.constants;

/**
 * @author Sunny
 * @date 2017/3/30
 */
public enum ConsumptionTypeEnum {
	
	/**
	 * 支出
	 */
	EXPENDITURE((byte)0x01),
	
	/**
	 * 收入
	 */
	INCOME((byte)0x02);
	
	private Byte value;
	
	ConsumptionTypeEnum (Byte value){
		this.value = value;
	}
	
	public Byte getValue() {
		return value;
	}

	public static ConsumptionTypeEnum getEnum(Byte val) {
		ConsumptionTypeEnum[] values = ConsumptionTypeEnum.values();
		for (ConsumptionTypeEnum object : values) {
			if (object.getValue().equals(val)) {
				return object;
			}
		}
		return null;
	}
}
