package com.lawu.eshop.game.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 好友拼图返回对象
 * 
 * @author lihj
 * @Date 2018年3月19日
 */
public class GamePuzzleRankReturnDTO {

	@ApiModelProperty(value = "游戏得分")
	private int gameScore;
	@ApiModelProperty(value = "游戏星星正数为加星星，负数为减星星")
	private int gameStar;
	@ApiModelProperty(value = "游戏积分正数为加星星，负数为减星星")
	private int gamePoint;
	private String userNum;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "头像")
	private String headImg;
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

	public int getGamePoint() {
		return gamePoint;
	}

	public void setGamePoint(int gamePoint) {
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

}
