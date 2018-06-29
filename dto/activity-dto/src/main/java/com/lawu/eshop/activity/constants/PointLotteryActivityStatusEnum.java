package com.lawu.eshop.activity.constants;

/**
 * 积分夺宝活动状态枚举
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
public enum PointLotteryActivityStatusEnum {
    
    /**
     * 未发布
     */
    UNPUBLISHED((byte) 0x01, "未发布"),
    
    /**
     * 进行中
     */
    PROCESSING((byte) 0x02, "进行中"),
    
    /**
     * 已发布(可参与)
     */
    PUBLISHED((byte) 0x03, "即将开始"),
    
    /**
     * 参与结束
     */
    PARTICIPATION_END((byte) 0x04, "参与结束"),
    
    /**
     * 已开奖
     */
    ALREADY_LOTTERY((byte) 0x05, "已开奖"),
    
    /**
     * 删除
     */
    DELETE((byte) 0x06, "删除");
    
    private Byte val;

    private String name;

    PointLotteryActivityStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }
    
    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
    
    public static PointLotteryActivityStatusEnum getEnum(Byte val) {
        PointLotteryActivityStatusEnum[] values = PointLotteryActivityStatusEnum.values();
        for (PointLotteryActivityStatusEnum object : values) {
            if (object.getVal().equals(val)) {
                return object;
            }
        }
        return null;
    }
}
