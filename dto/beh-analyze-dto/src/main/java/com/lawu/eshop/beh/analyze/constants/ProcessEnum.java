package com.lawu.eshop.beh.analyze.constants;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
public enum ProcessEnum {
    NORMAL((byte) 0x00),//未处理
    NOT_HANDLE((byte) 0x01),     //不处理
    FREEZE((byte) 0x02);    //冻结
    private Byte val;

    ProcessEnum(Byte val) {
        this.val = val;
    }

    public static ProcessEnum getEnum(Byte val) {
        ProcessEnum[] values = ProcessEnum.values();
        for (ProcessEnum object : values) {
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
