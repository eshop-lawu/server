package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BankAccountThirdAccountEditParam {

	@NotNull(message="id不能为空")
	@ApiParam (name="id",required = true, value = "主键")
	private Long id;

	@NotBlank(message="accountName不能为空")
	@ApiParam (name="accountName",required = true, value = "姓名")
	private String accountName;

	@NotBlank(message="accountNumber不能为空")
	@Length(min = 0, max = 26, message = "第三方账号长度不符合(26个字符以内)")
	@ApiParam (name="accountNumber",required = true, value = "第三方账号")
	private String accountNumber;

	@NotNull(message="bankAccountTypeEnum不能为空")
	@ApiParam (name="bankAccountTypeEnum",required = true, value = "类型（支付宝、微信）")
	private BankAccountTypeEnum bankAccountTypeEnum;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BankAccountTypeEnum getBankAccountTypeEnum() {
		return bankAccountTypeEnum;
	}

	public void setBankAccountTypeEnum(BankAccountTypeEnum bankAccountTypeEnum) {
		this.bankAccountTypeEnum = bankAccountTypeEnum;
	}
}
