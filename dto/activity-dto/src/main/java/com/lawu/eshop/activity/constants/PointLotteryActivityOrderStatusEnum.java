package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
public enum PointLotteryActivityOrderStatusEnum {

    /**
     * 待处理
     */
    PENDING((byte) 0x00, "待处理"),

    /**
     * 成功
     */
    SUCCESS((byte) 0x01, "成功"),

    /**
     * 积分扣除失败
     */
    FAILURE((byte) 0x02, "积分扣除失败");

    private Byte val;

    private String name;

    PointLotteryActivityOrderStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public static PointLotteryActivityOrderStatusEnum getEnum(Byte val) {
        PointLotteryActivityOrderStatusEnum[] values = PointLotteryActivityOrderStatusEnum.values();
        for (PointLotteryActivityOrderStatusEnum object : values) {
            if (object.getVal().equals(val)) {
                return object;
            }
        }
        return null;
    }
}
