package com.lawu.eshop.user.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class MerchantInviterParam extends AbstractPageParam{
	
	@ApiParam (name="name", value = "商家名称")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	

	
}
