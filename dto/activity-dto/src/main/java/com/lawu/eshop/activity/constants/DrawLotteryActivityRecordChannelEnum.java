package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public enum DrawLotteryActivityRecordChannelEnum {

    FREE_LOTTERY((byte) 0x00, "免费"),
    POINT_LOTTERY((byte) 0x01, "积分兑换");

    private Byte val;
    private String name;

    DrawLotteryActivityRecordChannelEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static DrawLotteryActivityRecordChannelEnum getEnum(Byte val) {
        DrawLotteryActivityRecordChannelEnum[] values = DrawLotteryActivityRecordChannelEnum.values();
        for (DrawLotteryActivityRecordChannelEnum object : values) {
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
