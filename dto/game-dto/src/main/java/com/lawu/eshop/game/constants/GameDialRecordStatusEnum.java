package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public enum GameDialRecordStatusEnum {

    POINT_DEDUCT_ING((byte) 0x00, "待处理"),
    TAKE_PART_LOTTERY((byte) 0x01, "已参与"),
    NOT_LOTTERY((byte) 0x02, "未中奖"),
    GET_LOTTERY((byte) 0x03, "已中奖"),
    GIVE_UP_TAKE_LOTTERY((byte) 0x04, "放弃领奖"),
    TAKE_LOTTERY((byte) 0x05, "已领奖"),
    SEND_LOTTERY((byte) 0x06, "奖品已发放"),
    POINT_DEDUCT_FAIL((byte) 0x07, "积分扣除失败");

    private Byte val;
    private String name;

    GameDialRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameDialRecordStatusEnum getEnum(Byte val) {
        GameDialRecordStatusEnum[] values = GameDialRecordStatusEnum.values();
        for (GameDialRecordStatusEnum object : values) {
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
