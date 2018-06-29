package com.lawu.eshop.cache.constants;

/**
 * 头脑PK参与记录状态枚举
 * 
 * @author jiangxinjun
 * @createDate 2018年3月13日
 * @updateDate 2018年3月13日
 */
public enum GameAttendRecordStatusEnum {
    
    /**
     * 初始化
     */
    INITSTATUS((byte) 0x00, "初始化"),
    
    /**
     * 参与成功
     */
    ATTENDSUCCESS((byte) 0x01, "参与成功"),
    
    /**
     * 参与失败
     */
    ATTENDFAIL((byte) 0x02, "参与失败"),
    
    /**
     * 游戏成功
     */
    GAMEPLAYSUCCESS((byte) 0x03, "游戏成功"),
    
    /**
     * 游戏失败
     */
    GAMEPLAYFAIL((byte) 0x04, "游戏失败"),
    
    /**
     * 退还
     */
    REFUND((byte) 0x05, "退还");

    GameAttendRecordStatusEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    private Byte val;
    
    private String name;

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
    
    public static GameAttendRecordStatusEnum getEnum(Byte val) {
        GameAttendRecordStatusEnum[] values = GameAttendRecordStatusEnum.values();
        for (GameAttendRecordStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
