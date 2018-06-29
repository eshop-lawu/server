package com.lawu.eshop.user.dto;

/**
 * zhangyong on 2017/3/28.
 */
public enum MerchantStatusEnum {

    MERCHANT_STATUS_UNCHECK((byte) 0x00),//未审核
    MERCHANT_STATUS_CHECKED((byte) 0x01),//审核通过
    MERCHANT_STATUS_CHECK_FAILED((byte) 0x02),//审核不通过
    MERCHANT_STATUS_NOT_MONEY((byte) 0x03),//未提交保证金
    MERCHANT_STATUS_GIVE_MONEY_CHECK((byte) 0x04),//已提交保证金待财务核实
    MERCHANT_STATUS_GIVE_MONEY_CHECK_FAILED((byte) 0x05),//财务审核不通过
    MERCHANT_STATUS_CANCEL((byte) 0x06);//注销

    public Byte val;

    MerchantStatusEnum(Byte val) {
        this.val = val;
    }

    public static MerchantStatusEnum getEnum(Byte val) {
        MerchantStatusEnum[] values = MerchantStatusEnum.values();
        for (MerchantStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
