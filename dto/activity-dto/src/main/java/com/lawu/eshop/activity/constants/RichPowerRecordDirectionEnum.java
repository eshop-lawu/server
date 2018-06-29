package com.lawu.eshop.activity.constants;

/**
 * @author lihj
 * @date 2018/5/2
 */
public enum RichPowerRecordDirectionEnum {

    IN((byte) 0x01, "收入"),

    OUT((byte) 0x02, "支出");
    private Byte val;

    private String name;

    RichPowerRecordDirectionEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static RichPowerRecordDirectionEnum getEnum(Byte val) {
        RichPowerRecordDirectionEnum[] values = RichPowerRecordDirectionEnum.values();
        for (RichPowerRecordDirectionEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
