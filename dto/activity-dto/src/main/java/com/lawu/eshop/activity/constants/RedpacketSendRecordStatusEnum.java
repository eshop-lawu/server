package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public enum RedpacketSendRecordStatusEnum {

    SENDING((byte) 0x01, "发放中"),
    SENT((byte) 0x02, "已发放待领取"),
    FAILED((byte) 0x03, "发放失败"),
    RECEIVED((byte) 0x04, "已领取"),
    RFUND_ING((byte) 0x05, "退款中"),
    REFUND((byte) 0x06, "已退款");

    private Byte val;

    private String name;

    RedpacketSendRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static RedpacketSendRecordStatusEnum getEnum(Byte val) {
        RedpacketSendRecordStatusEnum[] values = RedpacketSendRecordStatusEnum.values();
        for (RedpacketSendRecordStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public static String getName(Byte val) {
        RedpacketSendRecordStatusEnum[] values = RedpacketSendRecordStatusEnum.values();
        for (RedpacketSendRecordStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object.name;
            }
        }
        return null;
    }
}
