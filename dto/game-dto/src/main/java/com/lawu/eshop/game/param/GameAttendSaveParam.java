package com.lawu.eshop.game.param;

import java.util.List;

import com.lawu.eshop.cache.dto.GameCommonNumDTO;
import com.lawu.eshop.game.constants.AttendTypeEnum;

/**
 * 初始化参与记录保存参数
 * 
 * @author lihj
 * @Date 2018年3月13日
 */
public class GameAttendSaveParam {

	/**
	 * 当前用户userNum
	 */
	private String userNum;
	private String attendNum;
	private String userAccount;
	private boolean isFree;
	/**
	 * 扣除星星
	 */
	private int subStar;
	/**
	 * 游戏难度id
	 */
	private long difficultyId;

	/**
	 * 参与类型
	 */
	private AttendTypeEnum attendType;

	private List<String> userNums;
	private int picCount;
	/**
	 * 参与扣除积分
	 */
	private int attendPoint;

	/**
	 * 参与扣除星星
	 */
	private int attendStar;

	public long getDifficultyId() {
		return difficultyId;
	}

	public void setDifficultyId(long difficultyId) {
		this.difficultyId = difficultyId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}

	public List<String> getUserNums() {
		return userNums;
	}

	public void setUserNums(List<String> userNums) {
		this.userNums = userNums;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getSubStar() {
		return subStar;
	}

	public void setSubStar(int subStar) {
		this.subStar = subStar;
	}

	public int getPicCount() {
		return picCount;
	}

	public void setPicCount(int picCount) {
		this.picCount = picCount;
	}

	public int getAttendPoint() {
		return attendPoint;
	}

	public void setAttendPoint(int attendPoint) {
		this.attendPoint = attendPoint;
	}

	public int getAttendStar() {
		return attendStar;
	}

	public void setAttendStar(int attendStar) {
		this.attendStar = attendStar;
	}

	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean free) {
		isFree = free;
	}
}
