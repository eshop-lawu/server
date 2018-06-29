package com.lawu.eshop.cache.constants;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public enum GameRoomPlayerStatusEnum {

    NOT_READY((byte) 0x01, "未准备"),
    READY((byte) 0x02, "准备");

    private Byte val;
    private String name;

    GameRoomPlayerStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameRoomPlayerStatusEnum getEnum(Byte val) {
        GameRoomPlayerStatusEnum[] values = GameRoomPlayerStatusEnum.values();
        for (GameRoomPlayerStatusEnum object : values) {
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
