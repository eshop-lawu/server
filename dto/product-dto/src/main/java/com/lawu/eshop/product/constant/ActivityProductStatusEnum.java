package com.lawu.eshop.product.constant;

/**
 * 抢购活动商品状态枚举
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public enum ActivityProductStatusEnum {

    /**
     * 1-未审核
     */
    NOT_AUDITED((byte) 0x01),

    /**
     * 2-已审核
     */
    AUDITED((byte) 0x02),
    
    /**
     * 3-未通过
     */
    NOT_PASS((byte) 0x03);
    
    private Byte value;

    ActivityProductStatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static ActivityProductStatusEnum getEnum(Byte value) {
        for (ActivityProductStatusEnum item : ActivityProductStatusEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
