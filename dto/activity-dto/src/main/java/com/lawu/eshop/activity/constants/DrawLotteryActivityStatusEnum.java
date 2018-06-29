package com.lawu.eshop.activity.constants;

/**
 * @author meishuquan
 * @date 2018/01/12.
 */
public enum DrawLotteryActivityStatusEnum {

    UN_PUBLISH((byte) 0x01, "未发布"),
    LOTTERYING((byte) 0x02, "进行中"),
    PUBLISHED((byte) 0x03, "即将开始"),
    FINISHED((byte) 0x04, "已结束"),
    CANCEL((byte) 0x05, "下架"),
    DELETE((byte) 0x06, "删除");
    private Byte val;
    private String name;

    DrawLotteryActivityStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static DrawLotteryActivityStatusEnum getEnum(Byte val) {
        DrawLotteryActivityStatusEnum[] values = DrawLotteryActivityStatusEnum.values();
        for (DrawLotteryActivityStatusEnum object : values) {
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
