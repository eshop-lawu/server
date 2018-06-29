package com.lawu.eshop.game.dto;


import java.util.List;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleDifficultyDTO {
    /**
     * id
     */
	@ApiModelProperty(value = "id")
    private Long id;
    
    /**
     * 困难级别
     */
	@ApiModelProperty(value = "困难级别")
    private GameHardLevelEnum level;
   
    /**
     * 对应积分
     */
	@ApiModelProperty(value = "对应积分")
    private int point;
    
    /**
     * 对应星星
     */
	@ApiModelProperty(value = "对应星星")
    private int star;
	
	 /**
     * 困难程度
     */
    private int coefficient;
    
    private Integer challengeTime;
    
    private List<SecScoreDTO> secScores;
    
    /**
	 * 机器人答题时间（小）
	 */
	private Integer robotMinSecond;
	  
	/**
	 * 机器人答题时间（大）
	 */
	private Integer robotMaxSecond;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameHardLevelEnum getLevel() {
        return level;
    }

    public void setLevel(GameHardLevelEnum level) {
        this.level = level;
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

	public List<SecScoreDTO> getSecScores() {
		return secScores;
	}

	public void setSecScores(List<SecScoreDTO> secScores) {
		this.secScores = secScores;
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
