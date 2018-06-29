package com.lawu.eshop.product.constant;

/**
 * 抢购活动状态枚举
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public enum ActivityStatusEnum {

    /**
     * 1-未发布
     */
    UNPUBLISHED((byte) 0x01),
    
    /**
     * 2-发布中
     */
    PUBLISHED((byte) 0x02),
    
    /**
     * 3-审核中
     */
    IN_REVIEW((byte) 0x03),

    /**
     * 4-未开始
     */
    NOT_STARTED((byte) 0x04),
    
    /**
     * 5-进行中
     */
    PROCESSING((byte) 0x05),
    
    /**
     * 6-已结束
     */
    END((byte) 0x06);
    
    
    private Byte value;

    ActivityStatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static ActivityStatusEnum getEnum(Byte value) {
        for (ActivityStatusEnum item : ActivityStatusEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
