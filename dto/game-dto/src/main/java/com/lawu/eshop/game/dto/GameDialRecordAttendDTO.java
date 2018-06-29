package com.lawu.eshop.game.dto;

import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class GameDialRecordAttendDTO {

    @ApiModelProperty(value = "转盘游戏id")
    private Long gameDialId;

    @ApiModelProperty(value = "POINT_DEDUCT_ING--待处理，TAKE_PART_LOTTERY--已参与，POINT_DEDUCT_FAIL--积分扣除失败")
    private GameDialRecordStatusEnum statusEnum;

    public Long getGameDialId() {
        return gameDialId;
    }

    public void setGameDialId(Long gameDialId) {
        this.gameDialId = gameDialId;
    }

    public GameDialRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GameDialRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
