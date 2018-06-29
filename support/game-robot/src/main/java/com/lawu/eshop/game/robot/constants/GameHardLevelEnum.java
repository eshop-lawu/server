package com.lawu.eshop.game.robot.constants;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public enum GameHardLevelEnum {

    /**
     * 简单
     */
    SIMPLE((byte) 0x01, "简单"),

    /**
     * 一般
     */
    COMMONLY((byte) 0x02, "一般"),

    /**
     * 困难
     */
    DIFFICULTY((byte) 0x03, "困难"),

    /**
     * 随机
     */
    RANDOM((byte) 0x04, "随机");// 随机仅在开房使用

    private Byte val;
    private String name;

    GameHardLevelEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameHardLevelEnum getEnum(Byte val) {
        GameHardLevelEnum[] values = GameHardLevelEnum.values();
        for (GameHardLevelEnum object : values) {
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
