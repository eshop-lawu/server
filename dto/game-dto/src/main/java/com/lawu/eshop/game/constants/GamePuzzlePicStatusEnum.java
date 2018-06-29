package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public enum GamePuzzlePicStatusEnum {

    ENABLED((byte) 0x01, "启用"),
    DISABLED((byte) 0x02, "禁用");

    private Byte val;
    private String name;

    GamePuzzlePicStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GamePuzzlePicStatusEnum getEnum(Byte val) {
        GamePuzzlePicStatusEnum[] values = GamePuzzlePicStatusEnum.values();
        for (GamePuzzlePicStatusEnum object : values) {
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
