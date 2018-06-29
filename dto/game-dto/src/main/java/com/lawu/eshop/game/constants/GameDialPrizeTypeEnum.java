package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public enum GameDialPrizeTypeEnum {

    MONEY((byte) 0x01, "金额"),
    POINT((byte) 0x02, "积分"),
    STAR((byte) 0x03, "星星"),
    PRODUCT((byte) 0x04, "商品");

    private Byte val;
    private String name;

    GameDialPrizeTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameDialPrizeTypeEnum getEnum(Byte val) {
        GameDialPrizeTypeEnum[] values = GameDialPrizeTypeEnum.values();
        for (GameDialPrizeTypeEnum object : values) {
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
