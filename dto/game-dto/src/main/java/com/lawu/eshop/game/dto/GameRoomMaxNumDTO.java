package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/17.
 */
public class GameRoomMaxNumDTO {

    @ApiModelProperty(value = "房间最大人数")
    private Integer roomMaxNum;

    public Integer getRoomMaxNum() {
        return roomMaxNum;
    }

    public void setRoomMaxNum(Integer roomMaxNum) {
        this.roomMaxNum = roomMaxNum;
    }
}
