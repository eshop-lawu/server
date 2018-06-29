package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public enum DrawLotteryActivityRecordStatusEnum {

    POINT_DEDUCT_ING((byte) 0x00, "待处理"),
    TAKE_PART_LOTTERY((byte) 0x01, "已参与"),
    NOT_LOTTERY((byte) 0x02, "未中奖"),
    GET_LOTTERY((byte) 0x03, "已中奖"),
    GIVE_UP_TAKE_LOTTERY((byte) 0x04, "放弃领奖"),
    TAKE_LOTTERY((byte) 0x05, "已领奖"),
    POINT_DEDUCT_FAIL((byte) 0x06, "积分扣除失败"),
    SEND_LOTTERY((byte) 0x07, "奖品已发放");

    private Byte val;
    private String name;

    DrawLotteryActivityRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static DrawLotteryActivityRecordStatusEnum getEnum(Byte val) {
        DrawLotteryActivityRecordStatusEnum[] values = DrawLotteryActivityRecordStatusEnum.values();
        for (DrawLotteryActivityRecordStatusEnum object : values) {
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
