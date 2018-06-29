package com.lawu.eshop.game.srv.bo;

import java.util.Date;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;

/**
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleDifficultyBO {
    /**
     * id
     */
    private Long id;
    /**
     * 困难级别
     */
    private GameHardLevelEnum level;
    /**
     * 困难程度
     */
    private int coefficient;
    /**
     * 对应积分
     */
    private int point;
    
    private Integer challengeTime;
    
    private int star;
    private Date gmtModified;
    private Date gmtCreate;
    
    private String secScore;
    
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

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Integer getChallengeTime() {
		return challengeTime;
	}

	public void setChallengeTime(Integer challengeTime) {
		this.challengeTime = challengeTime;
	}

	public String getSecScore() {
		return secScore;
	}

	public void setSecScore(String secScore) {
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
