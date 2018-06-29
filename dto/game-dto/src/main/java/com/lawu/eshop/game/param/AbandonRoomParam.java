package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;

/**
 * @author yangqinghua
 * @date 2018/3/13.
 */
public class AbandonRoomParam {

    @ApiModelProperty(value = "是否房主")
    @NotNull(message="isRoomHost不能为空")
    private Boolean isRoomHost;

    @ApiModelProperty(value = "房间主键")
    @NotNull(message="roomId不能为空")
    private Long roomId;

    @ApiModelProperty(value = "玩家当前游戏状态（非房主退出时比传）")
    private GameRoomPlayerStatusEnum gameRoomPlayerStatusEnum;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public GameRoomPlayerStatusEnum getGameRoomPlayerStatusEnum() {
        return gameRoomPlayerStatusEnum;
    }

    public void setGameRoomPlayerStatusEnum(GameRoomPlayerStatusEnum gameRoomPlayerStatusEnum) {
        this.gameRoomPlayerStatusEnum = gameRoomPlayerStatusEnum;
    }

    public Boolean getRoomHost() {
        return isRoomHost;
    }

    public void setRoomHost(Boolean roomHost) {
        isRoomHost = roomHost;
    }
}
