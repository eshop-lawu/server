package com.lawu.eshop.property.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashBackageQueryParam extends AbstractPageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiParam(name = "content", value = "搜索文本")
	private String content;

	@ApiParam(name = "regionPath", value = "区域路径(格式：省ID/市ID/区ID)")
	private String regionPath;

	@ApiParam(name = "beginDate", required = true, value = "开始时间")
	private String beginDate;
	
	@ApiParam(name = "endDate", required = true, value = "结束时间")
	private String endDate;

	@ApiParam(name = "cashStatsuEnum", required = true, value = "提现状态(全部传空字符串)")
	private CashStatusEnum cashStatsuEnum;
	
	@ApiParam(name = "userTypeEnum", required = true, value = "用户类型")
	private UserTypeEnum userTypeEnum;

	@ApiParam(name = "bankAccountTypeEnum", required = true, value = "渠道(全部传空字符串)")
	private BankAccountTypeEnum bankAccountTypeEnum;
	
	//bootsrap table列排序
	private String sortName;
	private String sortOrder;
	
	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public CashStatusEnum getCashStatsuEnum() {
		return cashStatsuEnum;
	}

	public void setCashStatsuEnum(CashStatusEnum cashStatsuEnum) {
		this.cashStatsuEnum = cashStatsuEnum;
	}

	public UserTypeEnum getUserTypeEnum() {
		return userTypeEnum;
	}

	public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
		this.userTypeEnum = userTypeEnum;
	}

	public BankAccountTypeEnum getBankAccountTypeEnum() {
		return bankAccountTypeEnum;
	}

	public void setBankAccountTypeEnum(BankAccountTypeEnum bankAccountTypeEnum) {
		this.bankAccountTypeEnum = bankAccountTypeEnum;
	}
}
