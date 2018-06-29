package com.lawu.eshop.user.param;

import com.lawu.eshop.user.constants.InviterTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class EFriendQueryParam extends AbstractPageParam{

	@ApiParam (name="queryContent", value = "账号或昵称")
	private String queryContent;

	@ApiParam (required = false, value = "E友类型：MEMBER--会员，MERCHANT--商家")
	private InviterTypeEnum typeEnum;

	public String getQueryContent() {
		return queryContent;
	}

	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}

	public InviterTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(InviterTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
}
