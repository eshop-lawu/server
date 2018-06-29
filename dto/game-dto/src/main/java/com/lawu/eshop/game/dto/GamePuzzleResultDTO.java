package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 拼图游戏返回结果
 * 
 * @author lihj
 * @Date 2018年3月12日
 */
public class GamePuzzleResultDTO {

	@ApiModelProperty(value = "是否挑战成功,true成功，false失败")
	private boolean flag;

	@ApiModelProperty(value = "游戏分数，挑战成功即为增加分数，失败则为扣除积分")
	private int gameScore;
	@ApiModelProperty(value = "是否挑战成功,true答题结束，false答题未结束")
	private boolean isOver;
	@ApiModelProperty(value = "游戏星星，挑战成功即为增加星星，失败则为扣除星星")
	private int gameStar;
	private String userNum;
	@ApiModelProperty(value = "游戏排名")
	private int gameRank;
	@ApiModelProperty(value = "剩余多少人未拼图完毕")
	private int lastCount;
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getGameScore() {
		return gameScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	public int getGameStar() {
		return gameStar;
	}

	public void setGameStar(int gameStar) {
		this.gameStar = gameStar;
	}

	public int getGameRank() {
		return gameRank;
	}

	public void setGameRank(int gameRank) {
		this.gameRank = gameRank;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean over) {
		isOver = over;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public int getLastCount() {
		return lastCount;
	}

	public void setLastCount(int lastCount) {
		this.lastCount = lastCount;
	}
	
}
