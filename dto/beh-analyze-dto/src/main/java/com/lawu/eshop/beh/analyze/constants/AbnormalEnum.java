package com.lawu.eshop.beh.analyze.constants;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
public enum AbnormalEnum {
    NORMAL((byte) 0x00),//正常
    MAYBE_ABNORMAL((byte) 0x01),     //可能异常
    SURE_ABNORMAL((byte) 0x02);    //确认异常
    private Byte val;

    AbnormalEnum(Byte val) {
        this.val = val;
    }

    public static AbnormalEnum getEnum(Byte val) {
        AbnormalEnum[] values = AbnormalEnum.values();
        for (AbnormalEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }
}
