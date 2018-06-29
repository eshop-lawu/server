package com.lawu.eshop.cache.dto;

/**
 * @author lihj <br>
 * @date 2018/3/16
 */
public class GameCommonNumDTO {
    
    private String userNum;
    
    private boolean roomMaster;
    
    /**
     * 是否是机器人
     */
    private Boolean robot;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public boolean isRoomMaster() {
        return roomMaster;
    }

    public void setRoomMaster(boolean roomMaster) {
        this.roomMaster = roomMaster;
    }

    public Boolean getRobot() {
        return robot;
    }

    public void setRobot(Boolean robot) {
        this.robot = robot;
    }
    
}
