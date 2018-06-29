package com.lawu.eshop.mq.dto.user;

import com.lawu.compensating.transaction.Notification;

/**
 * @author zhangrc
 * @date 2018/02/23
 */
public class HelpRedpacktWxOpenIdNotification extends Notification {

	private static final long serialVersionUID = 2811043975865589395L;

	/**
	 * 用户编号
	 */
	private String userNum;
	
	/**
	 * 微信openId
	 */
	private String openId;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
	

   
}
