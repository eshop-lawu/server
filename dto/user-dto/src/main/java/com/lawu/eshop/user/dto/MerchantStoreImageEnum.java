package com.lawu.eshop.user.dto;

/**
 * Created by zhangyong on 2017/3/28.
 */
public enum MerchantStoreImageEnum {
    STORE_IMAGE_STORE((byte) 0x01),
    STORE_IMAGE_ENVIRONMENT((byte) 0x02),
    STORE_IMAGE_LOGO((byte) 0x03),
    STORE_IMAGE_LICENSE((byte) 0x04),
    STORE_IMAGE_OTHER((byte) 0x05),
    STORE_IMAGE_IDCARD((byte) 0x06);
    public Byte val;

    MerchantStoreImageEnum(Byte val) {
        this.val = val;
    }

    public static MerchantStoreImageEnum getEnum(Byte val) {
        MerchantStoreImageEnum[] values = MerchantStoreImageEnum.values();
        for (MerchantStoreImageEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
