package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

public class CashChargeParam {

	@ApiParam(name = "cashMoney", required = true, value = "提现金额")
	@NotBlank(message = "提现金额不能为空")
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "cashMoney格式错误要求数字或小数位不超过2位")
	private String cashMoney;

	@ApiParam(name = "businessBankAccountId", required = true, value = "提现账户ID")
	@NotNull(message = "提现账户不能为空")
	private Long businessBankAccountId;

	public String getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(String cashMoney) {
		this.cashMoney = cashMoney;
	}

	public Long getBusinessBankAccountId() {
		return businessBankAccountId;
	}

	public void setBusinessBankAccountId(Long businessBankAccountId) {
		this.businessBankAccountId = businessBankAccountId;
	}
}
