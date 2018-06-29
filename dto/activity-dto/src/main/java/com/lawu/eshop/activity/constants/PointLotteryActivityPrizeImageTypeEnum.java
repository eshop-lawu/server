package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public enum PointLotteryActivityPrizeImageTypeEnum {

    ACTIVITY_INTRODUCTIONS((byte) 0x01, "活动介绍"),
    LOTTERY_INFO((byte) 0x02, "中奖信息");

    private Byte val;
    private String name;

    PointLotteryActivityPrizeImageTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static PointLotteryActivityPrizeImageTypeEnum getEnum(Byte val) {
        PointLotteryActivityPrizeImageTypeEnum[] values = PointLotteryActivityPrizeImageTypeEnum.values();
        for (PointLotteryActivityPrizeImageTypeEnum object : values) {
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
