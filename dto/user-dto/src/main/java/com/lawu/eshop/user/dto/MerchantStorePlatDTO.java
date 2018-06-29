package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class MerchantStorePlatDTO {

	@ApiModelProperty(name = "merchantStoreId", value = "门店id")
	private Long merchantStoreId;
	/**
	 * 店铺名称 merchant_store.name
	 *
	 * @mbg.generated 2017-03-24 10:29:55
	 */
	@ApiModelProperty(name = "name", value = "店铺名称")
	private String name;
	
	@ApiModelProperty(name = "principalName", value = "负责人")
	private String principalName;

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	

}
