package com.lawu.eshop.user.constants;

/**
 * @author zhangyong
 * @date 2017/3/31.
 */
public enum MerchantAuditStatusEnum {

    MERCHANT_AUDIT_STATUS_UNCHECK((byte) 0x00),//未审核
    MERCHANT_AUDIT_STATUS_CHECKED((byte) 0x01),//审核通过
    MERCHANT_AUDIT_STATUS_CHECK_FAILED((byte) 0x02);//审核不通过
    public Byte val;

    MerchantAuditStatusEnum(Byte val) {
        this.val = val;
    }

    public static MerchantAuditStatusEnum getEnum(Byte val) {
        MerchantAuditStatusEnum[] values = MerchantAuditStatusEnum.values();
        for (MerchantAuditStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
