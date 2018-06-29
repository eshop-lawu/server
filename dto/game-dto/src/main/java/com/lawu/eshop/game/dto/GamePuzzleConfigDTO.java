package com.lawu.eshop.game.dto;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.common.constants.EnableEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public class GamePuzzleConfigDTO {
    /**
     *
     * game_puzzle_config.id
     */
	@ApiModelProperty(value = "id")
    private Long id;

    /**
     *
     * 每次参与花费积分
     * 
     */
	@ApiModelProperty(value = "每次参与花费积分")
    private Integer attendPoint;
	
	@ApiModelProperty(value = "每次参与最大花费积分")
    private Integer attendMaxPoint;

    /**
     *
     * 每个房间最大可参入人数
     *
     */
	@ApiModelProperty(value = "每个房间最大可参入人数")
    private Integer roomMaxNum;

    /**
     *
     * 分享可免费次数
     * 
     */
	@ApiModelProperty(value = "分享可免费次数")
    private Integer shareAttendCount;
	
	
	@ApiModelProperty(value = "免费次数")
    private Integer freeCount;

    /**
     *
     * 游戏时间长短
     *
     */
	@ApiModelProperty(value = "游戏时间长短")
    private Integer countDown;

    /**
     *
     * 挑战成功奖励积分
     * 
     */
	@ApiModelProperty(value = "挑战成功奖励积分")
    private Integer awardPoint;

    /**
     *
     * 挑战成功奖励星星
     * 
     */
	@ApiModelProperty(value = "挑战成功奖励星星")
    private Integer awardStar;

    /**
     *
     * 挑战失败扣除星星
     * 
     */
	@ApiModelProperty(value = "挑战失败扣除星星")
    private Integer deductStar;
	
	@ApiModelProperty(value = "图片个数")
    private Integer picCount;

    /**
     *
     * 倒计时对应评分{"list":[{"second":3,"score":200},{"second":5,"score":150},{"second":10,"score":100}]}
     * 
     */
	@ApiModelProperty(value = "倒计时对应评分")
    private List<SecScoreDTO> secScores;

    /**
     *
     * 游戏状态  0-禁用  1-启用
     * 
     */
	@ApiModelProperty(value = "游戏状态 ")
    private GameConfigStatusEnum statusEnum;
	
	
	@ApiModelProperty(value = "倒计时对应评分")
    private List<GamePointAllotDTO> allots;

    /**
     *
     * 更新时间
     * 
     */
	@ApiModelProperty(value = "更新时间 ")
    private Date gmtModified;
	
	private List<GamePuzzleDifficultyDTO> difficultys;
	
	@ApiModelProperty(value = "多少分数成功")
    private Integer successScore;
	
	private String forbiddenRemark;
	
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

	public GameConfigStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(GameConfigStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
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

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}

	public List<GamePuzzleDifficultyDTO> getDifficultys() {
		return difficultys;
	}

	public void setDifficultys(List<GamePuzzleDifficultyDTO> difficultys) {
		this.difficultys = difficultys;
	}

	public Integer getSuccessScore() {
		return successScore;
	}

	public void setSuccessScore(Integer successScore) {
		this.successScore = successScore;
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