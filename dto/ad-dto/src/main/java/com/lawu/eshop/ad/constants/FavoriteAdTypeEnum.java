package com.lawu.eshop.ad.constants;

public enum FavoriteAdTypeEnum {
	
	AD_TYPE_EGAIN((byte) 0x01),      //广告
	AD_TYPE_EPRAISE((byte) 0x02);     //e赞
    public Byte val;

    FavoriteAdTypeEnum(Byte val) {
        this.val = val;
    }

    public static FavoriteAdTypeEnum getEnum(Byte val) {
    	FavoriteAdTypeEnum[] values = FavoriteAdTypeEnum.values();
        for (FavoriteAdTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
