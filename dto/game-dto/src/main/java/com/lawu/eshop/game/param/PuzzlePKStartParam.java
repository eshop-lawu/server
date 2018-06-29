package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("拼图PK开始参数")
public class PuzzlePKStartParam {

	/**
	 * 参与类型
	 */
	private AttendTypeEnum attendType;
	@ApiModelProperty(value = "游戏难度SIMPLE简单，COMMONLY一般，DIFFICULTY困难，RANDOM随机")
	private GameHardLevelEnum gameLevel;
	@ApiModelProperty(value = "入场费消耗积分")
	private int costPoint;

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}

	public GameHardLevelEnum getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(GameHardLevelEnum gameLevel) {
		this.gameLevel = gameLevel;
	}

	public int getCostPoint() {
		return costPoint;
	}

	public void setCostPoint(int costPoint) {
		this.costPoint = costPoint;
	}
}
