package com.lawu.eshop.property.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 运营平台充值记录查询
 * </p>
 * @author Yangqh
 * @date 2017年5月16日 下午3:34:10
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RechargeQueryParam  extends AbstractPageParam{

	@ApiParam(name = "account", value = "账号")
	private String account;

	@ApiParam(name = "userType", required = true, value = "用户类型")
	private UserTypeEnum userType;
	
	@ApiParam(name = "status", value = "状态")
	private ThirdPayStatusEnum status;
	
	@ApiParam(name = "rechargeNumber", value = "充值单号")
	private String rechargeNumber;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	public ThirdPayStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ThirdPayStatusEnum status) {
		this.status = status;
	}

	public String getRechargeNumber() {
		return rechargeNumber;
	}

	public void setRechargeNumber(String rechargeNumber) {
		this.rechargeNumber = rechargeNumber;
	}

}
