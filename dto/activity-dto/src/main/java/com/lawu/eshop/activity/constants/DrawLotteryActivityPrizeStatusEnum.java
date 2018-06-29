package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public enum DrawLotteryActivityPrizeStatusEnum {

    VALID((byte) 0x01, "有效"),
    INVALID((byte) 0x02, "无效");

    private Byte val;
    private String name;

    DrawLotteryActivityPrizeStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static DrawLotteryActivityPrizeStatusEnum getEnum(Byte val) {
        DrawLotteryActivityPrizeStatusEnum[] values = DrawLotteryActivityPrizeStatusEnum.values();
        for (DrawLotteryActivityPrizeStatusEnum object : values) {
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
