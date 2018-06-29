package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户拼图请求参数
 *
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleGetPicParam {


    @ApiModelProperty(value = "参与类型STANDALONE[单机],RANDOM[随机]，MANYPEOPLE[多人]")
    private AttendTypeEnum attendType;
    @ApiModelProperty(value = "游戏困难级别SIMPLE[简单],COMMONLY[一般]，DIFFICULTY[困难]")
    private GameHardLevelEnum level;
    @ApiModelProperty("游戏难度id")
    private long difficultyId;

    public GameHardLevelEnum getLevel() {
        return level;
    }

    public void setLevel(GameHardLevelEnum level) {
        this.level = level;
    }

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }

	public long getDifficultyId() {
		return difficultyId;
	}

	public void setDifficultyId(long difficultyId) {
		this.difficultyId = difficultyId;
	}
    
}
