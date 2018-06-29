package com.lawu.eshop.game.srv.bo;

import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 根据困难程度出图返回结果
 *
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleGetPicBO {
    @ApiModelProperty(value = "困难度ID")
    private Long id;
    @ApiModelProperty(value = "图片路径")
    private String imgPath;
    @ApiModelProperty(value = "游戏困难级别SIMPLE[简单],COMMONLY[一般]，DIFFICULTY[困难]")
    private GameHardLevelEnum level;
    @ApiModelProperty(value = "参与类型STANDALONE[单机],RANDOM[随机]，MANYPEOPLE[多人]")
    private AttendTypeEnum attendType;
    @ApiModelProperty(value = "图片难度系数3(3*3),4(4*4),5(5*5)")
    private int coefficient;
    @ApiModelProperty(value = "当前图片最大得分")
    private int score;
    @ApiModelProperty(value = "当前图片可以花费时间")
    private int canUseTime;
    @ApiModelProperty(value = "key")
    private int key;
    @ApiModelProperty(value = "按要求需要打乱图片的序列英文逗号")
    private String cutImgNum;
    @ApiModelProperty(value = "图片提供者昵称")
    private String userNickname;
    @ApiModelProperty(value = "单机成功分数")
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
