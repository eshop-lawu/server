package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class MemberProfileDTO {
	
	@ApiModelProperty(value = "我的E友总数", required = true)
	private Integer inviteMemberCount;
	
	@ApiModelProperty(value = "我推荐的商家总数", required = true)
	private Integer inviteMerchantCount;

	public Integer getInviteMemberCount() {
		return inviteMemberCount;
	}

	public void setInviteMemberCount(Integer inviteMemberCount) {
		this.inviteMemberCount = inviteMemberCount;
	}

	public Integer getInviteMerchantCount() {
		return inviteMerchantCount;
	}

	public void setInviteMerchantCount(Integer inviteMerchantCount) {
		this.inviteMerchantCount = inviteMerchantCount;
	}
	
	

}
