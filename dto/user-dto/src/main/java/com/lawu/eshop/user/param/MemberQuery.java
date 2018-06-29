package com.lawu.eshop.user.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class MemberQuery extends AbstractPageParam{

	@ApiParam (name="accountOrNickName", value = "会员账号或者会员昵称")
	private String accountOrNickName;
	

	public String getAccountOrNickName() {
		return accountOrNickName;
	}

	public void setAccountOrNickName(String accountOrNickName) {
		this.accountOrNickName = accountOrNickName;
	}

	
	
}
