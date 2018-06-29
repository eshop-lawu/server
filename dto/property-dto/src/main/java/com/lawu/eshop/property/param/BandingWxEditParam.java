package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

public class BandingWxEditParam {

	@NotNull(message="id不能为空")
	@ApiParam (name="id",required = true, value = "主键")
	private Long id;

	@NotBlank(message="accountName不能为空")
	@ApiParam (name="accountName",required = true, value = "姓名")
	private String accountName;

	@ApiParam (name="code",value = "微信授权返回code，不修改传空字符串")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
