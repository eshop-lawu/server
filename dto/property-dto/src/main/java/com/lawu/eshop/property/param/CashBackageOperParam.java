package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.property.constants.CashOperEnum;

import io.swagger.annotations.ApiParam;

public class CashBackageOperParam {

	@ApiParam(name = "id",required = true, value = "提现ID(多条记录用英文逗号分隔)")
	@NotBlank(message="id不能为空")
	private String id;
	
	@ApiParam(name = "userNum",required = true, value = "提现用户编号(多条记录用英文逗号分隔)")
	@NotBlank(message="userNum不能为空")
	private String userNum;
	
	@ApiParam(name = "cashOperEnum", required = true, value = "操作类型(ACCEPT-受理；SUCCESS-成功；FAILURE-失败)")
	@NotNull(message="cashOperEnum不能为空")
	private CashOperEnum cashOperEnum;

	@ApiParam(name = "failReason", value = "失败原因")
	private String failReason;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CashOperEnum getCashOperEnum() {
		return cashOperEnum;
	}

	public void setCashOperEnum(CashOperEnum cashOperEnum) {
		this.cashOperEnum = cashOperEnum;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

}
