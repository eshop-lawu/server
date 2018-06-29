package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.GameRoomStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
public class GameRoomParam extends GameRoomCreateParam {

    @ApiModelProperty(value = "房主用户编号")
    private String userNum;

    @ApiModelProperty(value = "房主账号")
    private String account;

    @ApiModelProperty(value = "玩家信息")
    private String playerInfo;

    @ApiModelProperty(value = "房间状态")
    private GameRoomStatusEnum statusEnum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(String playerInfo) {
        this.playerInfo = playerInfo;
    }

    public GameRoomStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GameRoomStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
