package com.lawu.eshop.property.param;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashBackageQueryDetailParam  extends AbstractPageParam{

	@ApiParam(name = "userNum", value = "用户编号")
	@NotBlank(message="userNum不能为空")
	private String userNum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	

	

}
