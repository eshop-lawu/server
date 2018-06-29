package com.lawu.eshop.mall.constants;

/**
 * @author Sunny
 * @date 2017/3/29
 */
public enum SuggestionClientType {
	ANDROID((byte)1),
	IOS((byte)2);
	
	SuggestionClientType (Byte value) {
		this.value = value;
	}
	
	public Byte value;
	public static SuggestionClientType getEnum(Byte value) {
		SuggestionClientType[] values = SuggestionClientType.values();
		for (SuggestionClientType object : values) {
			if (object.value.equals(value)) {
				return object;
			}
		}
		return null;
	}
	
}
