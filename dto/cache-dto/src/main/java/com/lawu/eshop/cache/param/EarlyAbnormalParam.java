package com.lawu.eshop.cache.param;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
public class EarlyAbnormalParam {

    private String userNum;

    private Date time;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
