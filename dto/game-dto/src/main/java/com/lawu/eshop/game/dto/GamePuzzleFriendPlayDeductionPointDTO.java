package com.lawu.eshop.game.dto;

/**
 * 好友拼图扣除积分返回dto
 * @author lihj <br>
 * @date 2018/3/15
 */
public class GamePuzzleFriendPlayDeductionPointDTO {
    private String userNum;
    private boolean flag;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
