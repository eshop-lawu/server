package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lihj <br>
 * @date 2018/3/12
 */
public class GamePuzzleAttendRecordBO {
	private Long id;
	private String userNum;
	private String attendNum;
	private Byte attendType;
	private String roomNum;
	private Integer attendCount;
	private Byte difficulty;
	private Integer attendPoint;
	private Integer attendStar;
	private Long puzzlePicId;
	private Byte status;
	private Integer gameScore;
	private Integer gameRank;
	private Integer gameUseTime;
	private BigDecimal rewardPoint;
	private Integer rewardStar;
	private Date gmtModified;
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}

	public Byte getAttendType() {
		return attendType;
	}

	public void setAttendType(Byte attendType) {
		this.attendType = attendType;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public Byte getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Byte difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getAttendPoint() {
		return attendPoint;
	}

	public void setAttendPoint(Integer attendPoint) {
		this.attendPoint = attendPoint;
	}

	public Integer getAttendStar() {
		return attendStar;
	}

	public void setAttendStar(Integer attendStar) {
		this.attendStar = attendStar;
	}

	public Long getPuzzlePicId() {
		return puzzlePicId;
	}

	public void setPuzzlePicId(Long puzzlePicId) {
		this.puzzlePicId = puzzlePicId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getGameUseTime() {
		return gameUseTime;
	}

	public void setGameUseTime(Integer gameUseTime) {
		this.gameUseTime = gameUseTime;
	}

	public BigDecimal getRewardPoint() {
		return rewardPoint;
	}

	public void setRewardPoint(BigDecimal rewardPoint) {
		this.rewardPoint = rewardPoint;
	}

	public Integer getRewardStar() {
		return rewardStar;
	}

	public void setRewardStar(Integer rewardStar) {
		this.rewardStar = rewardStar;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getGameScore() {
		return gameScore;
	}

	public void setGameScore(Integer gameScore) {
		this.gameScore = gameScore;
	}

	public Integer getGameRank() {
		return gameRank;
	}

	public void setGameRank(Integer gameRank) {
		this.gameRank = gameRank;
	}

}
