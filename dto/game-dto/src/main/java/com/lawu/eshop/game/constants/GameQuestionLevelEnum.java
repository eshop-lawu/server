package com.lawu.eshop.game.constants;

/**
 * 
 * @author zhangrc
 * @date 2018-03-29
 */
public enum GameQuestionLevelEnum {
	
	SIMPLE((byte) 0x01,"简单"),
	DIFFICULTY((byte) 0x02,"困难");
	
	private Byte val;
	private String name;

	GameQuestionLevelEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}

	public static GameQuestionLevelEnum getEnum(Byte val) {
		GameQuestionLevelEnum[] values = GameQuestionLevelEnum.values();
		for (GameQuestionLevelEnum object : values) {
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
