package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameReadyParam {

    @ApiModelProperty(required = true, value = "房间id")
    private Long roomId;

    @ApiModelProperty(required = true, value = "NOT_READY--未准备，READY--准备")
    private GameRoomPlayerStatusEnum statusEnum;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public GameRoomPlayerStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GameRoomPlayerStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
