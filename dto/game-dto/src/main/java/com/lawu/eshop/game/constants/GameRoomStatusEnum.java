package com.lawu.eshop.game.constants;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public enum GameRoomStatusEnum {

    WAITING((byte) 0x01, "等待中"),
    PLAYING((byte) 0x02, "进行中"),
    FINISHED((byte) 0x03, "已结束");

    private Byte val;
    private String name;

    GameRoomStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameRoomStatusEnum getEnum(Byte val) {
        GameRoomStatusEnum[] values = GameRoomStatusEnum.values();
        for (GameRoomStatusEnum object : values) {
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
