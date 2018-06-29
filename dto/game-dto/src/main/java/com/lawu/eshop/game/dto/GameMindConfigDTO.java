package com.lawu.eshop.game.dto;

import java.util.List;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.common.constants.EnableEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author zhangrc
 * @date 2018年3月10日
 */
public class GameMindConfigDTO{
    /**
     * id
     */
	@ApiModelProperty(value = "id")
    private Long id;

    /**
     * 每次参与花费积分 
     */
	@ApiModelProperty(value = "每次参与花费积分")
    private Integer attendPoint;

    /**
     * 每次参与最大花费积分
     */
	@ApiModelProperty(value = "每次参与最大花费积分")
    private Integer attendMaxPoint;

    /**
     * 每个房间最大可参入人数
     */
	@ApiModelProperty(value = "每个房间最大可参入人数")
    private Integer roomMaxNum;
	
	@ApiModelProperty(value = "免费次数")
    private Integer freeCount;

    /**
     * 分享可免费次数
     */
	@ApiModelProperty(value = "分享可免费次数")
    private Integer shareAttendCount;

    /**
     * 单场游戏题目数量
     */
	@ApiModelProperty(value = "单场游戏题目数量")
    private Integer questionCount;

    /**
     * 单题游戏倒计时
     */
	@ApiModelProperty(value = "单题游戏倒计时")
    private Integer countDown;

    /**
     * 最后题目积分倍数
     */
	@ApiModelProperty(value = "最后题目积分倍数")
    private Integer lastScoreMultiple;

    /**
     * 挑战成功奖励积分
     */
	@ApiModelProperty(value = "挑战成功奖励积分")
    private Integer awardPoint;

    /**
     * 挑战成功奖励星星
     */
	@ApiModelProperty(value = "挑战成功奖励星星")
    private Integer awardStar;

    /**
     * 挑战失败扣除星星
     */
	@ApiModelProperty(value = "挑战失败扣除星星")
    private Integer deductStar;
	
	@ApiModelProperty(value = "多少分数成功")
    private Integer successScore;

    /**
     * 倒计时对应评分{"list":[{"second":3,"score":200},{"second":5,"score":150},{"second":10,"score":100}]}
     */
	@ApiModelProperty(value = "倒计时对应评分")
    private List<SecScoreDTO> secScores;

    /**
     * 游戏状态  0-禁用  1-启用
     */
	@ApiModelProperty(value = "游戏状态")
    private GameConfigStatusEnum statusEnum;

	@ApiModelProperty(value = "倒计时对应评分")
    private List<GamePointAllotDTO> allots;
	
	private List<GamePuzzleDifficultyDTO> difficulty;
	
	@ApiModelProperty(value = "简单题目个数")
	private Integer questionSimpleCount;
	
	private String forbiddenRemark;
	
	private Integer robotMinRightCount;

	private Integer robotMaxRightCount;

	private EnableEnum robotStatus;
	
	 private Integer robotEffectiveTime;

    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

   
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


	public List<SecScoreDTO> getSecScores() {
		return secScores;
	}


	public void setSecScores(List<SecScoreDTO> secScores) {
		this.secScores = secScores;
	}


	public List<GamePointAllotDTO> getAllots() {
		return allots;
	}


	public void setAllots(List<GamePointAllotDTO> allots) {
		this.allots = allots;
	}


	public GameConfigStatusEnum getStatusEnum() {
		return statusEnum;
	}

   

	public void setStatusEnum(GameConfigStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}


	public Integer getSuccessScore() {
		return successScore;
	}


	public void setSuccessScore(Integer successScore) {
		this.successScore = successScore;
	}


	public Integer getFreeCount() {
		return freeCount;
	}


	public void setFreeCount(Integer freeCount) {
		this.freeCount = freeCount;
	}


	public List<GamePuzzleDifficultyDTO> getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(List<GamePuzzleDifficultyDTO> difficulty) {
		this.difficulty = difficulty;
	}


	public Integer getQuestionSimpleCount() {
		return questionSimpleCount;
	}


	public void setQuestionSimpleCount(Integer questionSimpleCount) {
		this.questionSimpleCount = questionSimpleCount;
	}


	public String getForbiddenRemark() {
		return forbiddenRemark;
	}


	public void setForbiddenRemark(String forbiddenRemark) {
		this.forbiddenRemark = forbiddenRemark;
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