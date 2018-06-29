package com.lawu.eshop.ad.constants;

public enum FavoriteTypeEnum {
	
	AD_TYPE_FLAT((byte) 0x01),      //平面
	AD_TYPE_VIDEO((byte) 0x02);     //视频
    public Byte val;

    FavoriteTypeEnum(Byte val) {
        this.val = val;
    }

    public static FavoriteTypeEnum getEnum(Byte val) {
    	FavoriteTypeEnum[] values = FavoriteTypeEnum.values();
        for (FavoriteTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
