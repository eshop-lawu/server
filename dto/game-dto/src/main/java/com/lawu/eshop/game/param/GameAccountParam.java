package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameTypeEnum;

public class GameAccountParam {
	
	/**
	 * 游戏类型
	 */
	private GameTypeEnum type;
	
	/**
	 * 用户编号
	 */
	private String userNum;
	
	/**
	 * 用户账户
	 */
	private String account;

	public GameTypeEnum getType() {
		return type;
	}

	public void setType(GameTypeEnum type) {
		this.type = type;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	

}
