package com.lawu.eshop.game.robot.constants;

/**
 * 游戏状态枚举
 * 
 * @author zhangrc
 * @date 2018/3/10.
 */
public enum GameConfigStatusEnum {

    DISABLE((byte) 0x00, "禁用"),
    ENABLE((byte) 0x01, "启用");

    private Byte val;
    private String name;

    GameConfigStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameConfigStatusEnum getEnum(Byte val) {
        GameConfigStatusEnum[] values = GameConfigStatusEnum.values();
        for (GameConfigStatusEnum object : values) {
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
