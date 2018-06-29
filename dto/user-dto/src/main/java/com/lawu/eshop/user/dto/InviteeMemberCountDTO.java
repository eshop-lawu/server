package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class InviteeMemberCountDTO {
	
	@ApiModelProperty(value = "E友总数")
	private Integer inviteeMemberCount;

	public Integer getInviteeMemberCount() {
		return inviteeMemberCount;
	}

	public void setInviteeMemberCount(Integer inviteeMemberCount) {
		this.inviteeMemberCount = inviteeMemberCount;
	}
	
	

}
