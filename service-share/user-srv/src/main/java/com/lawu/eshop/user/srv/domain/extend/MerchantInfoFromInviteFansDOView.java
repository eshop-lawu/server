package com.lawu.eshop.user.srv.domain.extend;

public class MerchantInfoFromInviteFansDOView {

	private Long merchantStoreId;
	
	private String merchantStoreUrl;
	
	private String merchantStoreLogo;
	
	private String merchantStoreIntro;
	
	private String merchantStoreName;

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getMerchantStoreUrl() {
		return merchantStoreUrl;
	}

	public void setMerchantStoreUrl(String merchantStoreUrl) {
		this.merchantStoreUrl = merchantStoreUrl;
	}

	public String getMerchantStoreLogo() {
		return merchantStoreLogo;
	}

	public void setMerchantStoreLogo(String merchantStoreLogo) {
		this.merchantStoreLogo = merchantStoreLogo;
	}


	public String getMerchantStoreIntro() {
		return merchantStoreIntro;
	}

	public void setMerchantStoreIntro(String merchantStoreIntro) {
		this.merchantStoreIntro = merchantStoreIntro;
	}

	public String getMerchantStoreName() {
		return merchantStoreName;
	}

	public void setMerchantStoreName(String merchantStoreName) {
		this.merchantStoreName = merchantStoreName;
	}
}
