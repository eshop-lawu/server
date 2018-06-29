package com.lawu.eshop.mall.constants;

/**
 * @author Sunny
 * @date 2017/3/29
 */
public enum SuggestionUserType {
    /**
     * 商家
     */
    MEMBER((byte) 0x01),
    /**
     * 会员
     */
    MERCHANT((byte) 2);
    public Byte value;
    
    private String label;

    SuggestionUserType(Byte value) {
        this.value = value;
    }

    public static SuggestionUserType getEnum(Byte value) {
        SuggestionUserType[] values = SuggestionUserType.values();
        for (SuggestionUserType object : values) {
            if (object.value.equals(value)) {
                return object;
            }
        }
        return null;
    }

	public String getLabel() {
		return label;
	}

	
    

}
