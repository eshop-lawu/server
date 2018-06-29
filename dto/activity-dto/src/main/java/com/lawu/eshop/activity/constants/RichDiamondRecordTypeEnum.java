package com.lawu.eshop.activity.constants;

/**
 * @author lihj
 * @date 2018/5/2
 */
public enum RichDiamondRecordTypeEnum {

    ECOIN((byte) 0x01, "E钻"),

    LUCKCOIN((byte) 0x02, "幸运钻");
    private Byte val;

    private String name;

    RichDiamondRecordTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static RichDiamondRecordTypeEnum getEnum(Byte val) {
        RichDiamondRecordTypeEnum[] values = RichDiamondRecordTypeEnum.values();
        for (RichDiamondRecordTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
