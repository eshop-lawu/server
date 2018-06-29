package com.lawu.eshop.user.dto;

/**
 * @author zhangyong
 * @date 2017/4/24.
 */
public class MessagePushDTO {

    private String userNum;

    private String gtCid;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getGtCid() {
        return gtCid;
    }

    public void setGtCid(String gtCid) {
        this.gtCid = gtCid;
    }
}
