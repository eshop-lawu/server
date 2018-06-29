package com.lawu.eshop.game.constants;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public enum GameQuestionStatusEnum {
    DISABLE((byte) 0x00, "删除"),
    ENABLE((byte) 0x01, "正常");

    private Byte val;
    private String name;

    GameQuestionStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameQuestionStatusEnum getEnum(Byte val) {
        GameQuestionStatusEnum[] values = GameQuestionStatusEnum.values();
        for (GameQuestionStatusEnum object : values) {
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
