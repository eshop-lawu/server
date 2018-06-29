package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiModelProperty;

public class ListInviteFansWithContentParam extends ListInviteFansParam{

	@ApiModelProperty(value = "邀请类型：1——全选，2——取消几个不选其他都选的，3——只选勾中的", required = true)
	private byte inviteType;
	
	@ApiModelProperty(value = "选中的会员编号逗号拼接", required = false)
	private String nums;


	
	public byte getInviteType() {
		return inviteType;
	}

	public void setInviteType(byte inviteType) {
		this.inviteType = inviteType;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}
	
}
