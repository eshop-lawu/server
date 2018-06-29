package com.lawu.eshop.order.constants;

public enum RefundStatusEnum {

    /**
     * 0-待商家确认
     */
    TO_BE_CONFIRMED((byte) 0x00),

    /**
     * 1-填写退货地址
     */
    FILL_RETURN_ADDRESS((byte) 0x01),

    /**
     * 2-待退货
     */
    TO_BE_RETURNED((byte) 0x02),

    /**
     * 3-待退款
     */
    TO_BE_REFUNDED((byte) 0x03),

    /**
     * 4-退款成功
     */
    REFUND_SUCCESSFULLY((byte) 0x04),

    /**
     * 5-退款失败
     */
    REFUND_FAILED((byte) 0x05),

    /**
     * 6-平台介入
     */
    PLATFORM_INTERVENTION((byte) 0x06);

    private Byte value;

    RefundStatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static RefundStatusEnum getEnum(Byte value) {
        for (RefundStatusEnum item : RefundStatusEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
