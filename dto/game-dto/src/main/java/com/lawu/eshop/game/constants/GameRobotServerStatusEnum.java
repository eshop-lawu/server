package com.lawu.eshop.game.constants;

/**
 * 游戏机器人服务状态枚举类
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public enum GameRobotServerStatusEnum {
    
    /**
     * 禁用
     */
    DISABLED((byte) 0x00),
    
    /**
     * 启用
     */
    ENABLE((byte) 0x01);
    
    private Byte val;

    GameRobotServerStatusEnum(Byte val) {
        this.val = val;
    }

    public static GameRobotServerStatusEnum getEnum(Byte val) {
        GameRobotServerStatusEnum[] values = GameRobotServerStatusEnum.values();
        for (GameRobotServerStatusEnum object : values) {
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
