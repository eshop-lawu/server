package com.lawu.eshop.statistics.constants;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public enum GameTypeEnum {

    MIND((byte) 0x01, "头脑PK"),
    PUZZLE((byte) 0x02, "拼图"),
    DIAL((byte) 0x03, "转盘");

    private Byte val;
    private String name;

    GameTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameTypeEnum getEnum(Byte val) {
        GameTypeEnum[] values = GameTypeEnum.values();
        for (GameTypeEnum object : values) {
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
