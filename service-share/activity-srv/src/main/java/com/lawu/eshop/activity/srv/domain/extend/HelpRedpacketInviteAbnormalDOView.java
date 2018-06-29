package com.lawu.eshop.activity.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/3/1.
 */
public class HelpRedpacketInviteAbnormalDOView implements Serializable {
    private static final long serialVersionUID = -6196781795267046493L;

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
