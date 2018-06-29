package com.lawu.eshop.member.ws.channel;

import java.util.Date;

import com.lawu.eshop.game.constants.ConnType;

/**
 * @author Leach
 * @date 2018/3/29
 */
public class OfflineChannel {

    private ConnType type;

    private Date offlineTime;

    /**
     * 自主离线
     */
    private boolean offlineSelf;

    public OfflineChannel(ConnType type, Date offlineTime, boolean offlineSelf) {
        this.type = type;
        this.offlineTime = offlineTime;
        this.offlineTime = offlineTime;
    }

    public ConnType getType() {
        return type;
    }

    public void setType(ConnType type) {
        this.type = type;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public boolean isOfflineSelf() {
        return offlineSelf;
    }

    public void setOfflineSelf(boolean offlineSelf) {
        this.offlineSelf = offlineSelf;
    }
}
