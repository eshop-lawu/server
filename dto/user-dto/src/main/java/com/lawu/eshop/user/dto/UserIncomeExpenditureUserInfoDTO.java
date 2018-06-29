package com.lawu.eshop.user.dto;

/**
 * 统计用户支出和收入需要的用户信息
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class UserIncomeExpenditureUserInfoDTO {
	
	/**
	 * 用户编号
	 */
	private String userNum;
	
	/**
	 * 用户账号
	 */
	private String account;

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
