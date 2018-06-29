package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

public class IsExistsRedPacketDTO {
	
	@ApiModelProperty(value = "是否存在红包")
	private Boolean isExistsRedPacket;

	public Boolean getIsExistsRedPacket() {
		return isExistsRedPacket;
	}

	public void setIsExistsRedPacket(Boolean isExistsRedPacket) {
		this.isExistsRedPacket = isExistsRedPacket;
	}
	
	

}
