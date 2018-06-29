package com.lawu.eshop.mall.constants;

/**
 * @author zhangrc
 * @date 2017/9/25.
 */
public enum MobileTypeEnum {
	
    IOS((byte) 0x01),//ios
    
    Android((byte) 0x02);//安卓
	
    public Byte val;

    MobileTypeEnum(Byte val) {
        this.val = val;
    }

    public static MobileTypeEnum getEnum(Byte val) {
        MobileTypeEnum[] values = MobileTypeEnum.values();
        for (MobileTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
