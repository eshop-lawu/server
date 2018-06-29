package com.lawu.eshop.user.dto;

/**
 * Created by zhangyong on 2017/3/27.
 */
public enum CertifTypeEnum {
    CERTIF_TYPE_IDCARD((byte) 0x01),//身份证
    CERTIF_TYPE_LICENSE((byte) 0x02);//营业执照
    public Byte val;

    CertifTypeEnum(Byte val) {
        this.val = val;
    }

    public static CertifTypeEnum getEnum(Byte val) {
        CertifTypeEnum[] values = CertifTypeEnum.values();
        for (CertifTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
