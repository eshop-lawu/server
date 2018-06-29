package com.lawu.eshop.user.dto;

/**
 * 保存购物车所需要商家信息
 * 
 * @author Sunny
 * @date 2017年6月29日
 */
public class MerchantInfoForShoppingCartDTO {

    /**
    * 商家门店id
    */
    private Long merchantStoreId;
	
    /**
    * 店铺名称
    */
    private String merchantStoreName;

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getMerchantStoreName() {
		return merchantStoreName;
	}

	public void setMerchantStoreName(String merchantStoreName) {
		this.merchantStoreName = merchantStoreName;
	}
    
}
