package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class GamePuzzleRankReturnBO {
	@ApiModelProperty(value = "游戏得分")
	private int gameScore;
	@ApiModelProperty(value = "游戏星星正数为加星星，负数为减星星")
	private int gameStar;
	@ApiModelProperty(value = "游戏积分正数为加星星，负数为减星星")
	private BigDecimal gamePoint;
	private String userNum;
	
	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@ApiModelProperty(value = "游戏排名")
	private int gameRank;
	@ApiModelProperty(value = "总星星,减去游戏扣除后的")
	private int totalStar;

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

	public BigDecimal getGamePoint() {
		return gamePoint;
	}

	public void setGamePoint(BigDecimal gamePoint) {
		this.gamePoint = gamePoint;
	}

	public int getGameRank() {
		return gameRank;
	}

	public void setGameRank(int gameRank) {
		this.gameRank = gameRank;
	}

	public int getTotalStar() {
		return totalStar;
	}

	public void setTotalStar(int totalStar) {
		this.totalStar = totalStar;
	}
	
	

}
