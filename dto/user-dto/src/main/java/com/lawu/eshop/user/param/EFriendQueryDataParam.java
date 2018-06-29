package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiParam;

import java.util.List;

import com.lawu.framework.core.page.AbstractPageParam;

public class EFriendQueryDataParam extends EFriendQueryParam{

	private Long userId;

	private String userNum;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

}
