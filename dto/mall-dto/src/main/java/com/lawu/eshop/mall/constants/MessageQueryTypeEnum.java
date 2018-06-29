package com.lawu.eshop.mall.constants;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月17日
 */
public enum MessageQueryTypeEnum {

    NOTICE((byte) 0x01, "通知"),//通知
    BUSINESS((byte) 0x02, "交易");//交易

    private Byte val;
    private String name;

    public String getName() {
        return name;
    }

    public Byte getVal() {
        return val;
    }

    MessageQueryTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static MessageQueryTypeEnum getEnum(Byte val) {
        MessageQueryTypeEnum[] values = MessageQueryTypeEnum.values();
        for (MessageQueryTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
