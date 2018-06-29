package com.lawu.eshop.ad.constants;

/**
 * @author meishuquan
 * @date 2017/5/9.
 */
public enum AdPlatformStatusEnum {

    DELETE((byte) 0x00),      //删除
    UP((byte) 0x01),      //上架
    DOWN((byte) 0x02);    //下架
    public Byte val;


    AdPlatformStatusEnum(Byte val) {
        this.val = val;
    }

    public static AdPlatformStatusEnum getEnum(Byte val) {
        AdPlatformStatusEnum[] values = AdPlatformStatusEnum.values();
        for (AdPlatformStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
