package com.lawu.eshop.mall.constants;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public enum  RegionLevelEnum {
    REGION_LEVEL_ONE((byte) 0x01),     //门店词条
    REGION_LEVEL_TWO((byte) 0x02),
    REGION_LEVEL_THREE((byte) 0x03);//商品词条
    public Byte val;

    RegionLevelEnum(Byte val) {
        this.val = val;
    }

    public static RegionLevelEnum getEnum(Byte val) {
        RegionLevelEnum[] values = RegionLevelEnum.values();
        for (RegionLevelEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
