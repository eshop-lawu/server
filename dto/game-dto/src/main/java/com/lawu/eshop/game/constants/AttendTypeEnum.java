package com.lawu.eshop.game.constants;

/**
 * 参与类型
 *
 * @author lihj <br>
 * @date 2018/3/12
 */
public enum AttendTypeEnum {
    STANDALONE((byte) 0x01, "单机"),
    RANDOM((byte) 0x02, "随机"),
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
