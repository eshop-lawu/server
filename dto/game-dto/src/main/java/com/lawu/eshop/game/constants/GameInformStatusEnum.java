package com.lawu.eshop.game.constants;

/**
 * 游戏举报枚举
 * 
 * @author zhangrc
 * @date 2018/3/11.
 */
public enum GameInformStatusEnum {

	ON_DEAL_WITH((byte) 0x00, "待处理"),
    DEAL_WITH((byte) 0x01, "已处理"),
    UN_DEAL_WITH((byte) 0x02, "不处理");


    private Byte val;
    private String name;

    GameInformStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static GameInformStatusEnum getEnum(Byte val) {
        GameInformStatusEnum[] values = GameInformStatusEnum.values();
        for (GameInformStatusEnum object : values) {
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
