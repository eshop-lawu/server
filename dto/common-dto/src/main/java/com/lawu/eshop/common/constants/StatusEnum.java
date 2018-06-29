package com.lawu.eshop.common.constants;

/**
 * 数据状态枚举
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public enum StatusEnum {

    /**
     * 0-无效
     */
    INVALID((byte) 0x00),

    /**
     * 1-有效
     */
    VALID((byte) 0x01);

    private Byte value;
    
    StatusEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static StatusEnum getEnum(Byte value) {
        for (StatusEnum item : StatusEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
