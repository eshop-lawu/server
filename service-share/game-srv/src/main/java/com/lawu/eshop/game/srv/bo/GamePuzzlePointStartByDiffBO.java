package com.lawu.eshop.game.srv.bo;

import com.lawu.eshop.common.constants.GameScoreLevelEnum;

/**
 * 根据难度获取要得到的积分，星星
 * @author lihj
 * @Date 2018年3月17日
 */
public class GamePuzzlePointStartByDiffBO {

	/**
	 * 游戏加积分多少
	 */
	private int point;
	/**
	 * 游戏加多少星星
	 */
	private int star;
	
	/**
	 * 游戏得分
	 */
	private int score;
	
	/**
	 * 等级
	 */
	private GameScoreLevelEnum levelEnum;

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

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
