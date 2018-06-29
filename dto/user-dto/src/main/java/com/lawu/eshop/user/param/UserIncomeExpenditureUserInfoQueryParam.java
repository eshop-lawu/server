package com.lawu.eshop.user.param;

import java.util.List;

/**
 * 统计用户支出和收入,用于批量查询用户的账号
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class UserIncomeExpenditureUserInfoQueryParam {

	/**
	 * 用户编号列表
	 */
	private List<String> userNums;

	public List<String> getUserNums() {
		return userNums;
	}

	public void setUserNums(List<String> userNums) {
		this.userNums = userNums;
	}
	
}
