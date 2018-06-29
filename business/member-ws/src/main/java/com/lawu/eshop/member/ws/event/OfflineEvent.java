package com.lawu.eshop.member.ws.event;

import com.lawu.eshop.game.constants.ConnType;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author Leach
 * @date 2018/3/29
 */
public class OfflineEvent extends AsyncEvent {

    private String userNum;

    private ConnType connType;

    /**
     * 自主离线
     */
    private boolean offlineSelf;

    public OfflineEvent(Object source, String userNum, ConnType connType, boolean offlineSelf) {
        super(source);
        this.userNum = userNum;
        this.connType = connType;
        this.offlineSelf = offlineSelf;
    }

    public ConnType getConnType() {
        return connType;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public void setConnType(ConnType connType) {
        this.connType = connType;
    }

    public boolean isOfflineSelf() {
        return offlineSelf;
    }

    public void setOfflineSelf(boolean offlineSelf) {
        this.offlineSelf = offlineSelf;
    }
}
