package com.lawu.eshop.user.param;

import java.util.List;

public class ListInviteFansRealWithContentParam extends ListInviteFansRealParam{

	private Integer inviteType;
	
	private List<String> nums;

	public Integer getInviteType() {
		return inviteType;
	}

	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}

	public List<String> getNums() {
		return nums;
	}

	public void setNums(List<String> nums) {
		this.nums = nums;
	}
	
}
