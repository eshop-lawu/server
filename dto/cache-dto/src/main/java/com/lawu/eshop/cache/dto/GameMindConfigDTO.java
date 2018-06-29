package com.lawu.eshop.cache.dto;

import java.io.Serializable;
import java.util.List;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.common.constants.EnableEnum;
import com.lawu.eshop.common.param.GamePointAllotParam;
import com.lawu.eshop.common.param.SecScoreParam;

import io.swagger.annotations.ApiParam;

/**
 * @author zhangyong
 * @date 2018/3/16.
 */
public class GameMindConfigDTO implements Serializable{
    private static final long serialVersionUID = -612269280637861589L;

    private Integer attendPoint;


  //"每次参与最大花费积分"
    private Integer attendMaxPoint;


  // "每个房间最大可参入人数"
    private Integer roomMaxNum;

  // "免费次数"
    private Integer freeCount;

    //"分享可免费次数"
    private Integer shareAttendCount;


    // "单场游戏题目数量"
    private Integer questionCount;


  // "单题游戏倒计时"
    private Integer countDown;


   //"最后题目积分倍数"
    private Integer lastScoreMultiple;


   //"挑战成功奖励积分"
    private Integer awardPoint;


  //"挑战成功奖励星星"
    private Integer awardStar;


  //"挑战失败扣除星星"
    private Integer deductStar;

    //"挑战成功分数"
    private Integer successScore;

    //"倒计时对应评分"
    private List<SecScoreParam> secScore;


    private List<GamePointAllotParam> allotList;
    
    
	private GameConfigStatusEnum statusEnum;
	
	//备注
	private String forbiddenRemark;
	
	//简单题目个数
	private Integer questionSimpleCount;
	
	/**
	 * 答题正确个数（小）
	 */
	private Integer robotMinRightCount;
	  
	/**
	 * 答题正确个数（大）
	 */
	private Integer robotMaxRightCount;
	  
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

    public Integer getAttendMaxPoint() {
        return attendMaxPoint;
    }

    public void setAttendMaxPoint(Integer attendMaxPoint) {
        this.attendMaxPoint = attendMaxPoint;
    }

    public Integer getRoomMaxNum() {
        return roomMaxNum;
    }

    public void setRoomMaxNum(Integer roomMaxNum) {
        this.roomMaxNum = roomMaxNum;
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

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

    public Integer getLastScoreMultiple() {
        return lastScoreMultiple;
    }

    public void setLastScoreMultiple(Integer lastScoreMultiple) {
        this.lastScoreMultiple = lastScoreMultiple;
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

    public Integer getSuccessScore() {
        return successScore;
    }

    public void setSuccessScore(Integer successScore) {
        this.successScore = successScore;
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

	public Integer getQuestionSimpleCount() {
		return questionSimpleCount;
	}

	public void setQuestionSimpleCount(Integer questionSimpleCount) {
		this.questionSimpleCount = questionSimpleCount;
	}

	public Integer getRobotMinRightCount() {
		return robotMinRightCount;
	}

	public void setRobotMinRightCount(Integer robotMinRightCount) {
		this.robotMinRightCount = robotMinRightCount;
	}

	public Integer getRobotMaxRightCount() {
		return robotMaxRightCount;
	}

	public void setRobotMaxRightCount(Integer robotMaxRightCount) {
		this.robotMaxRightCount = robotMaxRightCount;
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
