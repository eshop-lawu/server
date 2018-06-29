package com.lawu.eshop.user.dto.param;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
public enum MerchantAuditTypeEnum {
    AUDIT_TYPE_STORE((byte) 0x01),//申请实体店
    AUDIT_TYPE_EDIT_INFO((byte) 0x02);//修改资料

    public Byte val;

    MerchantAuditTypeEnum(Byte val) {
        this.val = val;
    }

    public static MerchantAuditTypeEnum getEnum(Byte val) {
        MerchantAuditTypeEnum[] values = MerchantAuditTypeEnum.values();
        for (MerchantAuditTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
