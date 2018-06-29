package com.lawu.eshop.game.dto;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public class GameAttendStatusDTO {

    @ApiModelProperty(value = "INITSTATUS：初始化，ATTENDSUCCESS：参与成功，ATTENDFAIL：参与失败，GAMEPLAYSUCCESS：游戏成功，GAMEPLAYFAIL：游戏失败")
    private GameAttendRecordStatusEnum attendStatus;

    public GameAttendRecordStatusEnum getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(GameAttendRecordStatusEnum attendStatus) {
        this.attendStatus = attendStatus;
    }
}
