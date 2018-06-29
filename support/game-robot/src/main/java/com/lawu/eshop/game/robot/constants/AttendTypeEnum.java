package com.lawu.eshop.game.robot.constants;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public enum AttendTypeEnum {
    
    /**
     * 单机
     */
    STANDALONE((byte) 0x01, "单机"),
    
    /**
     * 随机
     */
    RANDOM((byte) 0x02, "随机"),
    
    /**
     * 多人
     */
    MANYPEOPLE((byte) 0x03, "多人");
    
    private Byte val;
    private String name;

    AttendTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static AttendTypeEnum getEnum(Byte val) {
        AttendTypeEnum[] values = AttendTypeEnum.values();
        for (AttendTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}
