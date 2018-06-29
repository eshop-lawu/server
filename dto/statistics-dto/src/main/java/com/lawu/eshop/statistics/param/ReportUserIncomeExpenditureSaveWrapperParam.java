package com.lawu.eshop.statistics.param;

import java.util.List;

/**
 * 用户收入和支出保存包装参数
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class ReportUserIncomeExpenditureSaveWrapperParam {
	
	/**
	 * 保存参数列表
	 */
	private List<ReportUserIncomeExpenditureSaveParam> params;

	public List<ReportUserIncomeExpenditureSaveParam> getParams() {
		return params;
	}

	public void setParams(List<ReportUserIncomeExpenditureSaveParam> params) {
		this.params = params;
	}
	
}
