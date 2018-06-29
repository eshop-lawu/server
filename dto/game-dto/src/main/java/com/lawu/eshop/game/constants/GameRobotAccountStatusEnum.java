package com.lawu.eshop.game.constants;

/**
 * 游戏机器人状态枚举类
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public enum GameRobotAccountStatusEnum {
    
    /**
     * 禁用
     */
    DISABLED((byte) 0x00),
    
    /**
     * 空闲
     */
    IDLE((byte) 0x01),
    
    /**
     * 使用中
     */
    USING((byte) 0x02);
    
    private Byte val;

    GameRobotAccountStatusEnum(Byte val) {
        this.val = val;
    }

    public static GameRobotAccountStatusEnum getEnum(Byte val) {
        GameRobotAccountStatusEnum[] values = GameRobotAccountStatusEnum.values();
        for (GameRobotAccountStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }
}
