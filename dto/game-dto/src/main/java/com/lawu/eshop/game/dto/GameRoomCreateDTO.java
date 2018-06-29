package com.lawu.eshop.game.dto;

/**
 * @author meishuquan
 * @date 2018/5/14.
 */
public class GameRoomCreateDTO {

    private Long roomId;

    private String roomNum;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
