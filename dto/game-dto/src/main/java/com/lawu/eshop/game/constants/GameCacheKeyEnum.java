package com.lawu.eshop.game.constants;

/**
 * 缓存key
 * @author lihj
 * @Date 2018年3月13日
 */
public enum GameCacheKeyEnum {

	RANDOM_READY("RANDOM_READY_","随机准备状态"),
	RANDOM_START("RANDOM_START_","随机开始状态"),
	MANYPEOPLE_NOREADY("MANYPEOPLE_NOREADY_","多人未准备状态"),
	MANYPEOPLE_READY("MANYPEOPLE_NOREADY_","多人准备状态"),
	PUZZLE_JSON("PUZZLE_JSON_","拼图对象"),
	PUZZLE_ANSWER("PUZZLE_ANSWER_","拼图答案正确"),
	PUZZLE_ANSWER_COUNT("PUZZLE_ANSWER_COUNT_","答题次数");
	private String val;
	private String name;
	GameCacheKeyEnum(String val,String name){
		this.val=val;
		this.name=name;
	}
	public String getVal() {
		return val;
	}
	public String getName() {
		return name;
	}
	
	public static GameCacheKeyEnum getEnum(String val){
		GameCacheKeyEnum[] values =GameCacheKeyEnum.values();
		for(GameCacheKeyEnum object :values){
			if(object.val.equals(val)){
				return object;
			}
		}
		return null;
	}
	
	
}
