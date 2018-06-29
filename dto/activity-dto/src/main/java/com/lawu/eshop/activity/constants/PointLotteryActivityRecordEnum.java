package com.lawu.eshop.activity.constants;

/**
 * 积分夺宝活动参与状态枚举
 * @author zhangrc
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
public enum PointLotteryActivityRecordEnum {
    
    /**
     * 未开奖
     */
    NO_OPEN_LOTTERY((byte) 0x01, "未开奖"),
    
    /**
     * 已中奖
     */
    WIN_LOTTERY((byte) 0x02, "已中奖"),
    
    /**
     * 未中奖
     */
    NO_WIN_LOTTERY((byte) 0x03, "未中奖");
    
    private Byte val;

    private String name;

    PointLotteryActivityRecordEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }
    
    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
    
    public static PointLotteryActivityRecordEnum getEnum(Byte val) {
        PointLotteryActivityRecordEnum[] values = PointLotteryActivityRecordEnum.values();
        for (PointLotteryActivityRecordEnum object : values) {
            if (object.getVal().equals(val)) {
                return object;
            }
        }
        return null;
    }
}
