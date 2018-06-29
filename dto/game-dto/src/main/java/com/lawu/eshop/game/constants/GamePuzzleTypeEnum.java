package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public enum GamePuzzleTypeEnum {

    PUZZLE((byte) 0x01, "拼图");

    private Byte val;
    private String name;

    GamePuzzleTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GamePuzzleTypeEnum getEnum(Byte val) {
        GamePuzzleTypeEnum[] values = GamePuzzleTypeEnum.values();
        for (GamePuzzleTypeEnum object : values) {
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
