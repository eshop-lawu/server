package com.lawu.eshop.property.param;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BackagePropertyinfoDataParam extends BackagePropertyinfoParam {

	@NotBlank(message = "userNum不能为空")
	private String userNum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	

	

}
