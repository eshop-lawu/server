package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public enum GameDialStatusEnum {

    ENABLED((byte) 0x01, "启用"),
    DISABLED((byte) 0x02, "禁用");

    private Byte val;
    private String name;

    GameDialStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameDialStatusEnum getEnum(Byte val) {
        GameDialStatusEnum[] values = GameDialStatusEnum.values();
        for (GameDialStatusEnum object : values) {
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
