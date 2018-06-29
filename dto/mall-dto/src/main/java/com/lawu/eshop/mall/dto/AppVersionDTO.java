package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

public class AppVersionDTO {
	
	@ApiModelProperty(value = "版本号")
	private String appVersion;
	
	@ApiModelProperty(value = "大版本号")
	private Integer appVersionCode;
	
	@ApiModelProperty(value = "更新内容")
	private String updateDesc;
	
	@ApiModelProperty(value = "下载地址")
	private String downloadUrl;
	
	@ApiModelProperty(value = "是否强制更新")
	private Boolean isForce;
	
	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(Integer appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getUpdateDesc() {
		return updateDesc;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Boolean getIsForce() {
		return isForce;
	}

	public void setIsForce(Boolean isForce) {
		this.isForce = isForce;
	}
}
