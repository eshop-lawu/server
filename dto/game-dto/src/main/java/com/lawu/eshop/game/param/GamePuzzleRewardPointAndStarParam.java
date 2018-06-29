package com.lawu.eshop.game.param;

/**
 * 拼图成功添加积分以及星星
 * @author lihj
 * @Date 2018年3月13日
 */
public class GamePuzzleRewardPointAndStarParam {

	private String userNum;
	private int point;
	private int star;
	private String attendNum;
	/**
	 * 游戏名次
	 */
	private int gameRank;
	/**
	 * 游戏用时
	 */
	private int gameUseTime;
	/**
	 * 得分
	 */
	private int socre;
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
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
	public String getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}
	public int getGameRank() {
		return gameRank;
	}
	public void setGameRank(int gameRank) {
		this.gameRank = gameRank;
	}
	public int getGameUseTime() {
		return gameUseTime;
	}
	public void setGameUseTime(int gameUseTime) {
		this.gameUseTime = gameUseTime;
	}
	public int getSocre() {
		return socre;
	}
	public void setSocre(int socre) {
		this.socre = socre;
	}
	
	
	
}
