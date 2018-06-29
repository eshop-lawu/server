package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import com.lawu.eshop.property.constants.UserTypeEnum;

import io.swagger.annotations.ApiParam;

public class CashBackageQuerySumParam {

	@ApiParam(name = "userTypeEnum", required = true, value = "用户类型")
	@NotNull(message="userTypeEnum不能为空")
	private UserTypeEnum userTypeEnum;

	public UserTypeEnum getUserTypeEnum() {
		return userTypeEnum;
	}

	public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
		this.userTypeEnum = userTypeEnum;
	}

}
