package com.lawu.eshop.activity.constants;

public enum HelpTypeEnum {

    LOGIN((byte) 0x01, "登陆助力"),
    REF((byte) 0x02, "注册助力");
    private Byte val;

    private String name;

    HelpTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static HelpTypeEnum getEnum(Byte val) {
        HelpTypeEnum[] values = HelpTypeEnum.values();
        for (HelpTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public static String getName(Byte val) {
        HelpTypeEnum[] values = HelpTypeEnum.values();
        for (HelpTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object.name;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }

    public void setVal(Byte val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
