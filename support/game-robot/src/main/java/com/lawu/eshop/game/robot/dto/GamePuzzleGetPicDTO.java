package com.lawu.eshop.game.robot.dto;


import java.util.List;

import com.lawu.eshop.game.robot.constants.AttendTypeEnum;
import com.lawu.eshop.game.robot.constants.GameHardLevelEnum;

/**
 * 根据困难程度出图返回结果
 *
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleGetPicDTO {

    private Long id;
    private String imgPath;
    private GameHardLevelEnum level;
	private AttendTypeEnum attendType;
    private int coefficient;
    private int canUseTime;
    private int score;
    private int key;
    private String cutImgNum;
    private String userNickname;
    private int singleMaxScore;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getCutImgNum() {
        return cutImgNum;
    }

    public void setCutImgNum(String cutImgNum) {
        this.cutImgNum = cutImgNum;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}

	public int getCanUseTime() {
		return canUseTime;
	}

	public void setCanUseTime(int canUseTime) {
		this.canUseTime = canUseTime;
	}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

	public int getSingleMaxScore() {
		return singleMaxScore;
	}

	public void setSingleMaxScore(int singleMaxScore) {
		this.singleMaxScore = singleMaxScore;
	}
    
    
}
