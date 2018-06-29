package com.lawu.eshop.user.constants;

/**
 * 冻结类型
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
public enum FreezeTypeEnum {
    
    /**
     * 高频访问
     */
    HIGH_FREQUENCY_ACCESS((byte) 0x01),
    
    /**
     * 机器注册
     */
    ROBOT_REGISTRATION((byte) 0x02),
    
    /**
     * 后台冻结
     */
    BACKGROUND_FREEZE((byte) 0x03);
    
    private Byte value;
    
    FreezeTypeEnum(Byte value) {
        this.value = value;
    }
    
    public Byte getValue() {
        return value;
    }

    public static FreezeTypeEnum getEnum(Byte value) {
        FreezeTypeEnum[] values = FreezeTypeEnum.values();
        for (FreezeTypeEnum object : values) {
            if (object.getValue().equals(value)) {
                return object;
            }
        }
        return null;
    }
}
