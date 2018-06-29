package com.lawu.eshop.user.dto;

import java.util.List;

/**
 * 统计用户支出和收入需要的用户信息包装DTO
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class UserIncomeExpenditureUserInfoWrapperDTO {
	
	/**
	 * 用户信息列表
	 */
	List<UserIncomeExpenditureUserInfoDTO> userInfoList;

	public List<UserIncomeExpenditureUserInfoDTO> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserIncomeExpenditureUserInfoDTO> userInfoList) {
		this.userInfoList = userInfoList;
	}
	
}
