package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class InviteeMechantCountDTO {
	
	@ApiModelProperty(value = "邀请的商家总数")
	private Integer inviteeMechantCount;

	public Integer getInviteeMechantCount() {
		return inviteeMechantCount;
	}

	public void setInviteeMechantCount(Integer inviteeMechantCount) {
		this.inviteeMechantCount = inviteeMechantCount;
	}
	
	
	
	

}
