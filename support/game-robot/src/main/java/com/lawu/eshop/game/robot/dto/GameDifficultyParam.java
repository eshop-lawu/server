package com.lawu.eshop.game.robot.dto;


import java.io.Serializable;
import java.util.List;

import com.lawu.eshop.game.robot.constants.GameHardLevelEnum;


/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class GameDifficultyParam implements Serializable{

	private static final long serialVersionUID = 1819242617984604046L;
	private GameHardLevelEnum leverEnum;
	
	private  int coefficient;
	
	private Integer challengeTime;
	
    private List<SecScoreParam> secScore;
	
	/**
	 * 机器人拼图最小时间（小）
	 */
	private Integer robotMinSecond;
	  
	/**
	 * 机器人拼图最大时间（大）
	 */
	private Integer robotMaxSecond;

	public GameHardLevelEnum getLeverEnum() {
		return leverEnum;
	}

	public void setLeverEnum(GameHardLevelEnum leverEnum) {
		this.leverEnum = leverEnum;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	
	public Integer getChallengeTime() {
		return challengeTime;
	}

	public void setChallengeTime(Integer challengeTime) {
		this.challengeTime = challengeTime;
	}
	
	
	public List<SecScoreParam> getSecScore() {
		return secScore;
	}

	public void setSecScore(List<SecScoreParam> secScore) {
		this.secScore = secScore;
	}

	public Integer getRobotMinSecond() {
		return robotMinSecond;
	}

	public void setRobotMinSecond(Integer robotMinSecond) {
		this.robotMinSecond = robotMinSecond;
	}

	public Integer getRobotMaxSecond() {
		return robotMaxSecond;
	}

	public void setRobotMaxSecond(Integer robotMaxSecond) {
		this.robotMaxSecond = robotMaxSecond;
	}
	
	

}
