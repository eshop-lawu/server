package com.lawu.eshop.game.robot.param;

public class PuzzlePKSubmitParam {

    /**
     * 游戏id
     */
    private String gameId;

    /**
     * 拼图序列
     */
    private String picNum;

    /**
     * 参与编号
     */
    private String attendNum;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPicNum() {
        return picNum;
    }

    public void setPicNum(String picNum) {
        this.picNum = picNum;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

}
