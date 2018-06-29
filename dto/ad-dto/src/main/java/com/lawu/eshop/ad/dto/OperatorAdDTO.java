package com.lawu.eshop.ad.dto;

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class OperatorAdDTO {
	
	private Long id;
	
	private String title;
	
	private Long merchantId;
	
	@ApiModelProperty(name = "principalName", value = "负责人")
	private String principalName;
	
	private AdEgainTypeEnum typeEnum;
	
	

	public AdEgainTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(AdEgainTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
