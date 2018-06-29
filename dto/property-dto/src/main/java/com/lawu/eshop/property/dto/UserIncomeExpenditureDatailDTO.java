package com.lawu.eshop.property.dto;

import java.util.List;

/**
 * 用户支出收入详情DTO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class UserIncomeExpenditureDatailDTO {

	/**
	 * 用户支出收入列表
	 */
	private List<UserIncomeExpenditureDTO> userIncomeExpenditureList;

	public List<UserIncomeExpenditureDTO> getUserIncomeExpenditureList() {
		return userIncomeExpenditureList;
	}

	public void setUserIncomeExpenditureList(List<UserIncomeExpenditureDTO> userIncomeExpenditureList) {
		this.userIncomeExpenditureList = userIncomeExpenditureList;
	}
	
}