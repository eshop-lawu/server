package com.lawu.eshop.game.constants;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public enum StarRecordStatusEnum {
    INCREASE((byte) 0x01, "增加"),
    REDUCE((byte) 0x02, "减少");
    private Byte val;
    private String name;

    StarRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static StarRecordStatusEnum getEnum(Byte val) {
        StarRecordStatusEnum[] values = StarRecordStatusEnum.values();
        for (StarRecordStatusEnum object : values) {
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
