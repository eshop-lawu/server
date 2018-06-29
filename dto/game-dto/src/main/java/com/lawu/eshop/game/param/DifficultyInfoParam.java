package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;

/**
 * 根据时间获取不同困难数据
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月17日
 */
public class DifficultyInfoParam {
	
	/**
	 * 难度系数
	 */
	private GameHardLevelEnum levelEnum;
	
	/**
	 * 时间
	 */
	private int second;

	public GameHardLevelEnum getLevelEnum() {
		return levelEnum;
	}

	public void setLevelEnum(GameHardLevelEnum levelEnum) {
		this.levelEnum = levelEnum;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	
	

}
