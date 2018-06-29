package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserRedPacketDTO {
	
	@ApiModelProperty(value = "会员id")
	private Long memberId;

	@ApiModelProperty(value = "用户编号")
	private String userNum;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	
	

}
