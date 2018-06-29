package com.lawu.eshop.mall.constants;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public enum WindowMessageTypeEnum {

    POINT_LOTTERY((byte) 0x01, "积分抽奖");

    public Byte val;
    private String name;

    public String getName() {
        return name;
    }

    public Byte getVal() {
        return val;
    }

    WindowMessageTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static WindowMessageTypeEnum getEnum(Byte val) {
        WindowMessageTypeEnum[] values = WindowMessageTypeEnum.values();
        for (WindowMessageTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
