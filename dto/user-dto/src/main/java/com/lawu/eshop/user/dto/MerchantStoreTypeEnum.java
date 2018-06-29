package com.lawu.eshop.user.dto;

/**
 *
 * Created by Administrator on 2017/3/27.
 */
public enum  MerchantStoreTypeEnum {
    NORMAL_MERCHANT((byte)0x01),//普通商铺
    ENTITY_MERCHANT((byte)0x02);//实体商铺
    public Byte val;
    MerchantStoreTypeEnum(Byte val) {
        this.val = val;
    }

    public static MerchantStoreTypeEnum getEnum(Byte val){
        MerchantStoreTypeEnum[] values = MerchantStoreTypeEnum.values();
        for (MerchantStoreTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
