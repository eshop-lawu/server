package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.common.constants.GameScoreLevelEnum;

public class GamePuzzleResultBO {
	//拼图是否成功
	private boolean flag;
	//得分
	private int gameScore;
	//是否结束
	private boolean isOver;
	/**
	 * 游戏得分
	 */
	private BigDecimal gamePoint;
	private int gameStar;
	private int gameRank;
	private int resultCode;
	private String userNum;
	/**
	 * 剩余多少人未答题完毕
	 */
	private int lastCount;
	
	private GameScoreLevelEnum scoreLevel;
	
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
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public boolean isOver() {
		return isOver;
	}
	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}
	public BigDecimal getGamePoint() {
		return gamePoint;
	}
	public void setGamePoint(BigDecimal gamePoint) {
		this.gamePoint = gamePoint;
	}
	public GameScoreLevelEnum getScoreLevel() {
		return scoreLevel;
	}
	public void setScoreLevel(GameScoreLevelEnum scoreLevel) {
		this.scoreLevel = scoreLevel;
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
