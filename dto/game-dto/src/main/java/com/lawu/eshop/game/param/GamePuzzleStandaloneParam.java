package com.lawu.eshop.game.param;

/**
 * 单机拼图 游戏loadding参数
 * 
 * @author lihj
 * @Date 2018年3月15日
 */
public class GamePuzzleStandaloneParam {
	private String userNum;
/*	private long difficultyId;
	private AttendTypeEnum attendType;
	private GameHardLevelEnum level;*/
	private int subStar;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	/*public long getDifficultyId() {
		return difficultyId;
	}

	public void setDifficultyId(long difficultyId) {
		this.difficultyId = difficultyId;
	}

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}

	public GameHardLevelEnum getLevel() {
		return level;
	}

	public void setLevel(GameHardLevelEnum level) {
		this.level = level;
	}*/

	public int getSubStar() {
		return subStar;
	}

	public void setSubStar(int subStar) {
		this.subStar = subStar;
	}

}
