package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;

import io.swagger.annotations.ApiParam;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BackagePropertyinfoParam {

	@ApiParam(name = "account", required = true, value = "充值账号")
	@NotBlank(message="account不能为空")
	private String account;

	@ApiParam(name = "userTypeEnum", required = true, value = "用户类型")
	@NotNull(message="userTypeEnum操作不能为空")
	private UserTypeEnum userTypeEnum;
	
	@ApiParam(name = "payTypeEnum", required = true, value = "余额或积分")
	@NotNull(message="payTypeEnum操作不能为空")
	private PayTypeEnum payTypeEnum;

	@NotBlank(message = "money不能为空")
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "money格式错误要求数字或小数位不超过2位")
	private String money;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public UserTypeEnum getUserTypeEnum() {
		return userTypeEnum;
	}

	public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
		this.userTypeEnum = userTypeEnum;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public PayTypeEnum getPayTypeEnum() {
		return payTypeEnum;
	}

	public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
		this.payTypeEnum = payTypeEnum;
	}

}
