package com.lawu.eshop.cache.param;

import java.util.List;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.common.constants.EnableEnum;
import com.lawu.eshop.common.param.GamePointAllotParam;

import io.swagger.annotations.ApiParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public class GamePuzzleConfigParam {
  
	@ApiParam (name="attendPoint",required = true, value = "每次参与花费积分")
    private Integer attendPoint;

	@ApiParam (name="roomMaxNum",required = true, value = "每个房间最大可参入人数")
    private Integer roomMaxNum;
	
	@ApiParam (name="attendMaxPoint",required = true, value = "每次参与最大花费积分")
    private Integer attendMaxPoint;
	
	@ApiParam (name="freeCount",required = true, value = "免费次数")
    private Integer freeCount;

	@ApiParam (name="shareAttendCount",required = true, value = "分享可免费次数")
    private Integer shareAttendCount;

	@ApiParam (name="countDown",required = true, value = "游戏时间长短")
    private Integer countDown;
   
	@ApiParam (name="awardPoint",required = true, value = "挑战成功奖励积分")
    private Integer awardPoint;

	@ApiParam (name="awardStar",required = true, value = "挑战成功奖励星星")
    private Integer awardStar;

	@ApiParam (name="deductStar",required = true, value = "挑战失败扣除星星")
    private Integer deductStar;
	
	@ApiParam (name="picCount",required = true, value = "拼图数量")
    private Integer picCount;
	
	@ApiParam (name="successScore",required = true, value = "挑战成功分数")
    private Integer successScore;

	@ApiParam (name="allotList",required = true)
	private List<GamePointAllotParam> allotList;
	
	@ApiParam (name="difficultys",required = true)
	private List<GameDifficultyParam> difficultys;
	
	@ApiParam (name="statusEnum",required = true)
	private GameConfigStatusEnum statusEnum;
	
	@ApiParam (name="forbiddenRemark",value = "禁用说明")
	private String forbiddenRemark;
	
	/**
	 * 机器人状态
	 */
	private EnableEnum robotStatus;
	
	private Integer robotEffectiveTime;

	public Integer getAttendPoint() {
		return attendPoint;
	}

	public void setAttendPoint(Integer attendPoint) {
		this.attendPoint = attendPoint;
	}

	public Integer getRoomMaxNum() {
		return roomMaxNum;
	}

	public void setRoomMaxNum(Integer roomMaxNum) {
		this.roomMaxNum = roomMaxNum;
	}

	public Integer getShareAttendCount() {
		return shareAttendCount;
	}

	public void setShareAttendCount(Integer shareAttendCount) {
		this.shareAttendCount = shareAttendCount;
	}

	public Integer getCountDown() {
		return countDown;
	}

	public void setCountDown(Integer countDown) {
		this.countDown = countDown;
	}

	public Integer getAwardPoint() {
		return awardPoint;
	}

	public void setAwardPoint(Integer awardPoint) {
		this.awardPoint = awardPoint;
	}

	public Integer getAwardStar() {
		return awardStar;
	}

	public void setAwardStar(Integer awardStar) {
		this.awardStar = awardStar;
	}

	public Integer getDeductStar() {
		return deductStar;
	}

	public void setDeductStar(Integer deductStar) {
		this.deductStar = deductStar;
	}


	public List<GamePointAllotParam> getAllotList() {
		return allotList;
	}

	public void setAllotList(List<GamePointAllotParam> allotList) {
		this.allotList = allotList;
	}

	public Integer getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(Integer freeCount) {
		this.freeCount = freeCount;
	}

	public List<GameDifficultyParam> getDifficultys() {
		return difficultys;
	}

	public void setDifficultys(List<GameDifficultyParam> difficultys) {
		this.difficultys = difficultys;
	}

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}

	public Integer getAttendMaxPoint() {
		return attendMaxPoint;
	}

	public void setAttendMaxPoint(Integer attendMaxPoint) {
		this.attendMaxPoint = attendMaxPoint;
	}

	public Integer getSuccessScore() {
		return successScore;
	}

	public void setSuccessScore(Integer successScore) {
		this.successScore = successScore;
	}

	public GameConfigStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(GameConfigStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getForbiddenRemark() {
		return forbiddenRemark;
	}

	public void setForbiddenRemark(String forbiddenRemark) {
		this.forbiddenRemark = forbiddenRemark;
	}

	public EnableEnum getRobotStatus() {
		return robotStatus;
	}

	public void setRobotStatus(EnableEnum robotStatus) {
		this.robotStatus = robotStatus;
	}

	public Integer getRobotEffectiveTime() {
		return robotEffectiveTime;
	}

	public void setRobotEffectiveTime(Integer robotEffectiveTime) {
		this.robotEffectiveTime = robotEffectiveTime;
	}

	
    
}