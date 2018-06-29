package com.lawu.eshop.mall.constants;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
public enum LotteryActivityStatusEnum {

    UN_PUBLISH((byte) 0x00, "未发布"),
    LOTTERYING((byte) 0x01, "进行中"),
    PUBLISHED((byte) 0x02, "即将开始"),
    FINISHED((byte) 0x03, "已结束"),
    LOTTERYED((byte) 0x04, "已开奖"),
    CANCEL((byte) 0x05, "下架"),
    DELETE((byte) 0x06, "删除");
    private Byte val;
    private String name;

    LotteryActivityStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static LotteryActivityStatusEnum getEnum(Byte val) {
        LotteryActivityStatusEnum[] values = LotteryActivityStatusEnum.values();
        for (LotteryActivityStatusEnum object : values) {
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
