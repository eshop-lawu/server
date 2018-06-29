package com.lawu.eshop.mq.dto.user;

import java.util.Date;

import com.lawu.compensating.transaction.Notification;

/**
 * @author Leach
 * @date 2017/3/29
 */
public class RegNotification extends Notification {
	
	private static final long serialVersionUID = 5248272122861770528L;
	
	private String userNum;

	private String inviteNum;

	private String inviteAccount;

	private Date gmtCreate;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(String inviteNum) {
        this.inviteNum = inviteNum;
    }

    public String getInviteAccount() {
        return inviteAccount;
    }

    public void setInviteAccount(String inviteAccount) {
        this.inviteAccount = inviteAccount;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
