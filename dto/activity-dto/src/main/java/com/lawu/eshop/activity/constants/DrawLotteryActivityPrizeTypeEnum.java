package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/2/28.
 */
public enum DrawLotteryActivityPrizeTypeEnum {

    MONEY((byte) 0x01, "金额"),
    POINT((byte) 0x02, "积分"),
    PRODUCT((byte) 0x03, "商品");

    private Byte val;
    private String name;

    DrawLotteryActivityPrizeTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static DrawLotteryActivityPrizeTypeEnum getEnum(Byte val) {
        DrawLotteryActivityPrizeTypeEnum[] values = DrawLotteryActivityPrizeTypeEnum.values();
        for (DrawLotteryActivityPrizeTypeEnum object : values) {
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
