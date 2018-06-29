package com.lawu.eshop.common.constants;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月10日
 */
public enum EnableEnum {

    /**
     * 0-禁用
     */
    DISABLED((byte) 0x00),

    /**
     * 1-启用
     */
    ENABLED((byte) 0x01);

    private Byte value;
    
    EnableEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static EnableEnum getEnum(Byte value) {
        for (EnableEnum item : EnableEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
