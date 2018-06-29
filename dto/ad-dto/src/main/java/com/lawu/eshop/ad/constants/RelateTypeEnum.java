package com.lawu.eshop.ad.constants;

public enum RelateTypeEnum {
	
	/**
	 * 商品
	 */
	PRODUCT_TYPE((byte) 0x01, "商品"),
	
	/**
	 * 店铺
	 */
	MERCHANT_STORE_TYPE((byte) 0x02, "店铺");
	
    private Byte val;
    
    private String name;

    public String getName() {
        return name;
    }

    public Byte getVal() {
		return val;
	}

	RelateTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static RelateTypeEnum getEnum(Byte val) {
    	RelateTypeEnum[] values = RelateTypeEnum.values();
        for (RelateTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
