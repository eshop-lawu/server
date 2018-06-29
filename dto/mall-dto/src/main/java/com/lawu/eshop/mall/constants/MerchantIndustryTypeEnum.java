package com.lawu.eshop.mall.constants;

/**
 * @author meishuquan
 * @date 2017/8/9.
 */
public enum MerchantIndustryTypeEnum {

    COMMON_INDUSTRY((byte) 0x01),     //普通
    ENTITY_INDUSTRY((byte) 0x02);      //实体
    private Byte val;

    public Byte getVal() {
        return val;
    }

    MerchantIndustryTypeEnum(Byte val) {
        this.val = val;
    }

    public static MerchantIndustryTypeEnum getEnum(Byte val) {
        MerchantIndustryTypeEnum[] values = MerchantIndustryTypeEnum.values();
        for (MerchantIndustryTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
