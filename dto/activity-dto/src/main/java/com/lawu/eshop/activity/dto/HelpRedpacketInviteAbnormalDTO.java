package com.lawu.eshop.activity.dto;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/3/1.
 */
public class HelpRedpacketInviteAbnormalDTO {


    private Date minInviteTime;

    private Date maxInviteTime;

    public Date getMinInviteTime() {
        return minInviteTime;
    }

    public void setMinInviteTime(Date minInviteTime) {
        this.minInviteTime = minInviteTime;
    }

    public Date getMaxInviteTime() {
        return maxInviteTime;
    }

    public void setMaxInviteTime(Date maxInviteTime) {
        this.maxInviteTime = maxInviteTime;
    }
}
