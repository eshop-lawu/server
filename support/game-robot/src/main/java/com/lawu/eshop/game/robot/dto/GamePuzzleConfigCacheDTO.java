package com.lawu.eshop.game.robot.dto;

import java.io.Serializable;
import java.util.List;

import com.lawu.eshop.game.robot.constants.EnableEnum;
import com.lawu.eshop.game.robot.constants.GameConfigStatusEnum;

/**
 * @author zhangyong
 * @date 2018/3/16.
 */
public class GamePuzzleConfigCacheDTO implements Serializable{
    private static final long serialVersionUID = 7113412504900196581L;
    private Integer attendPoint;

   //"每个房间最大可参入人数"
    private Integer roomMaxNum;

   //"每次参与最大花费积分"
    private Integer attendMaxPoint;

    // "免费次数"
    private Integer freeCount;

    // "分享可免费次数"
    private Integer shareAttendCount;

   //"游戏时间长短"
    private Integer countDown;

   //"挑战成功奖励积分"
    private Integer awardPoint;

   //"挑战成功奖励星星"
    private Integer awardStar;

    // "挑战失败扣除星星"
    private Integer deductStar;

 // "拼图数量"
    private Integer picCount;

   //"倒计时对应评分"
    private List<SecScoreParam> secScore;

    private List<GamePointAllotParam> allotList;

    private List<GameDifficultyParam> difficultys;
    
    //"挑战成功分数"
    private Integer successScore;
    
	private GameConfigStatusEnum statusEnum;
	
	//备注说明
	private String forbiddenRemark;
	
	/**
	 * 机器人状态
	 */
	private EnableEnum robotStatus;
	
	/**
	 * 机器人生效时间
	 */
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

    public Integer getAttendMaxPoint() {
        return attendMaxPoint;
    }

    public void setAttendMaxPoint(Integer attendMaxPoint) {
        this.attendMaxPoint = attendMaxPoint;
    }

    public Integer getFreeCount() {
        return freeCount;
    }

    public void setFreeCount(Integer freeCount) {
        this.freeCount = freeCount;
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

    public Integer getPicCount() {
        return picCount;
    }

    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    public List<SecScoreParam> getSecScore() {
        return secScore;
    }

    public void setSecScore(List<SecScoreParam> secScore) {
        this.secScore = secScore;
    }

    public List<GamePointAllotParam> getAllotList() {
        return allotList;
    }

    public void setAllotList(List<GamePointAllotParam> allotList) {
        this.allotList = allotList;
    }

    public List<GameDifficultyParam> getDifficultys() {
        return difficultys;
    }

    public void setDifficultys(List<GameDifficultyParam> difficultys) {
        this.difficultys = difficultys;
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
