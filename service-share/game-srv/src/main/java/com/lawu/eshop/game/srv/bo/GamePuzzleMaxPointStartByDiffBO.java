package com.lawu.eshop.game.srv.bo;

import com.lawu.eshop.common.constants.GameScoreLevelEnum;

/**
 * 根据难度获取要得到的积分，星星
 * @author lihj
 * @Date 2018年3月17日
 */
public class GamePuzzleMaxPointStartByDiffBO {

	/**
	 * 游戏得分
	 */
	private int score;
	
	/**
	 * 对应等级
	 */
	private GameScoreLevelEnum levelEnum;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GameScoreLevelEnum getLevelEnum() {
		return levelEnum;
	}

	public void setLevelEnum(GameScoreLevelEnum levelEnum) {
		this.levelEnum = levelEnum;
	}
	
	
	
}
