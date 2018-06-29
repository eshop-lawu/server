package com.lawu.eshop.property.param;

import com.lawu.eshop.property.constants.UserTypeEnum;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class AlipayOauthParam {

	@NotBlank(message = "code不能为空")
	private String code;

	@NotNull(message = "userTypeEnum不能为空")
	private UserTypeEnum userTypeEnum;

	@NotBlank(message = "userNum不能为空")
	private String userNum;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UserTypeEnum getUserTypeEnum() {
		return userTypeEnum;
	}

	public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
		this.userTypeEnum = userTypeEnum;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
}