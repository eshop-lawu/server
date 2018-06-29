package com.lawu.eshop.property.param;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

public class BandingWxParam {

	@NotBlank(message="accountName不能为空")
	@ApiParam (name="accountName",required = true, value = "姓名")
	private String accountName;

	@NotBlank(message="code不能为空")
	@ApiParam (name="code",required = true, value = "微信授权返回code")
	private String code;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
