package com.lawu.eshop.cache.constants;

/**
 * 
 * @author lihj
 * @date 2018-03-09
 */
public enum GameHardLevelEnum {
	
	SIMPLE((byte) 0x01,"简单"),
	COMMONLY((byte) 0x02,"一般"),
	DIFFICULTY((byte) 0x03,"困难"),
	RANDOM((byte)0x04,"随机");//随机仅在开房使用
	
	private Byte val;
	private String name;

	GameHardLevelEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}

	public static GameHardLevelEnum getEnum(Byte val) {
		GameHardLevelEnum[] values = GameHardLevelEnum.values();
		for (GameHardLevelEnum object : values) {
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
