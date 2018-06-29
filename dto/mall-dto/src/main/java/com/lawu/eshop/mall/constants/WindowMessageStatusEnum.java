package com.lawu.eshop.mall.constants;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public enum WindowMessageStatusEnum {

    ENABLE((byte) 0x01, "启用"),
    DISENABLE((byte) 0x02, "禁用");

    public Byte val;
    private String name;

    public String getName() {
        return name;
    }

    public Byte getVal() {
        return val;
    }

    WindowMessageStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static WindowMessageStatusEnum getEnum(Byte val) {
        WindowMessageStatusEnum[] values = WindowMessageStatusEnum.values();
        for (WindowMessageStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
