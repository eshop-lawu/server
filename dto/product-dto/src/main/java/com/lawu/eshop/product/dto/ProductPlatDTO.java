package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProductPlatDTO {
	
	@ApiModelProperty(value = "商品ID")
	private Long id;

	@ApiModelProperty(value = "商品名称")
	private String name;
	
	@ApiModelProperty(value = "商家ID")
	private Long merchantId;
	
	@ApiModelProperty(name = "principalName", value = "负责人")
	private String principalName;
	

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	

}
