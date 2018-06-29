package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;

import javax.validation.constraints.NotNull;

/**
 * @author yangqinghua
 * @date 2018/3/13.
 */
public class GameExitKickParam{

    @ApiModelProperty(value = "用户编号")
    @NotBlank(message="userNum不能为空")
    private String userNum;

    @ApiModelProperty(value = "房间主键")
    @NotNull(message="roomId不能为空")
    private Long roomId;

    @ApiModelProperty(value = "玩家当前游戏状态")
    @NotNull(message="gameRoomPlayerStatusEnum不能为空")
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

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}
