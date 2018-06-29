package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public enum GameDialPrizeStatusEnum {

    VALID((byte) 0x01, "有效"),
    INVALID((byte) 0x02, "无效");

    private Byte val;
    private String name;

    GameDialPrizeStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameDialPrizeStatusEnum getEnum(Byte val) {
        GameDialPrizeStatusEnum[] values = GameDialPrizeStatusEnum.values();
        for (GameDialPrizeStatusEnum object : values) {
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
