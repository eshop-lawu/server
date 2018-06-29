package com.lawu.eshop.user.constants;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public enum MerchantFavoredTypeEnum {

    TYPE_FULL((byte) 0x01),//每满
    TYPE_FULL_REDUCE((byte) 0x02),//满减
    TYPE_DISCOUNT((byte) 0x03);//折扣
    public Byte val;

    MerchantFavoredTypeEnum(Byte val) {
        this.val = val;
    }

    public static MerchantFavoredTypeEnum getEnum(Byte val) {
        MerchantFavoredTypeEnum[] values = MerchantFavoredTypeEnum.values();
        for (MerchantFavoredTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
