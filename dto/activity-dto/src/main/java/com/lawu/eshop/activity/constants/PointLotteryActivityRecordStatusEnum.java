package com.lawu.eshop.activity.constants;

/**
 * 积分夺宝活动记录状态枚举
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
public enum PointLotteryActivityRecordStatusEnum {

    /**
     * 未中奖
     */
    NOT_WINNING((byte) 0x01, "未中奖"),

    /**
     * 中奖
     */
    WINNING((byte) 0x02, "中奖");
    
    private Byte val;

    private String name;

    PointLotteryActivityRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }
    
    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
    
    public static PointLotteryActivityRecordStatusEnum getEnum(Byte val) {
        PointLotteryActivityRecordStatusEnum[] values = PointLotteryActivityRecordStatusEnum.values();
        for (PointLotteryActivityRecordStatusEnum object : values) {
            if (object.getVal().equals(val)) {
                return object;
            }
        }
        return null;
    }
}
