package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public enum GamePuzzleUserPicStatusEnum {

    CHECK_PENDING((byte) 0x01, "待审核"),
    HAVE_USE((byte) 0x02, "已采用"),
    NOT_USE((byte) 0x03, "未采用");

    private Byte val;
    private String name;

    GamePuzzleUserPicStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GamePuzzleUserPicStatusEnum getEnum(Byte val) {
        GamePuzzleUserPicStatusEnum[] values = GamePuzzleUserPicStatusEnum.values();
        for (GamePuzzleUserPicStatusEnum object : values) {
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
