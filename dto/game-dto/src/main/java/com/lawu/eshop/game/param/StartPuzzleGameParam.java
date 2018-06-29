package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 开始拼图游戏参数
 * 
 * @author lihj
 * @Date 2018年3月13日
 */
public class StartPuzzleGameParam {

	@ApiModelProperty("游戏难度id")
	private long difficultyId;

	@ApiModelProperty(value = "参与类型STANDALONE[单机],RANDOM[随机]，MANYPEOPLE[多人]")
	private AttendTypeEnum attendType;
	
	@ApiModelProperty(value = "难度SIMPLE[简单],COMMONLY[一般]，DIFFICULTY[困难]")
	private GameHardLevelEnum level;

	public long getDifficultyId() {
		return difficultyId;
	}

	public void setDifficultyId(long difficultyId) {
		this.difficultyId = difficultyId;
	}

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}

	public GameHardLevelEnum getLevel() {
		return level;
	}

	public void setLevel(GameHardLevelEnum level) {
		this.level = level;
	}
	



}
