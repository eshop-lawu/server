package com.lawu.eshop.common.constants;

/**
 * 分数等级  第一等级为最高
 * 
 * @author zhangrc
 * @createDate 2018年03月20日
 * @updateDate 2018年03月20日
 */
public enum GameScoreLevelEnum {

	
    LEVEL_1((byte) 0x01),
    
    LEVEL_2((byte) 0x02),
    
    LEVEL_3((byte) 0x03),
    
    LEVEL_4((byte) 0x04),
    
    LEVEL_5((byte) 0x05),
    
    LEVEL_6((byte) 0x06);

    private Byte value;
    
    GameScoreLevelEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static GameScoreLevelEnum getEnum(Byte value) {
        for (GameScoreLevelEnum item : GameScoreLevelEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}
