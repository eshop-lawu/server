package com.lawu.eshop.mall.dto;

public class AppFunctionManageInfoDTO {
	
	private Long id;

	private Boolean isEnableGame;

	private Boolean isEnableLove;
	
	private Boolean isEnableRich;

	private Boolean isEnable;

	private String appVersion;
	
	private Boolean isEnableMerchantRich;

	private String appMerchantVersion;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsEnableGame() {
		return isEnableGame;
	}

	public void setIsEnableGame(Boolean isEnableGame) {
		this.isEnableGame = isEnableGame;
	}

	public Boolean getIsEnableLove() {
		return isEnableLove;
	}

	public void setIsEnableLove(Boolean isEnableLove) {
		this.isEnableLove = isEnableLove;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Boolean getIsEnableRich() {
		return isEnableRich;
	}

	public void setIsEnableRich(Boolean isEnableRich) {
		this.isEnableRich = isEnableRich;
	}

	public Boolean getIsEnableMerchantRich() {
		return isEnableMerchantRich;
	}

	public void setIsEnableMerchantRich(Boolean isEnableMerchantRich) {
		this.isEnableMerchantRich = isEnableMerchantRich;
	}

	public String getAppMerchantVersion() {
		return appMerchantVersion;
	}

	public void setAppMerchantVersion(String appMerchantVersion) {
		this.appMerchantVersion = appMerchantVersion;
	}
	

}
