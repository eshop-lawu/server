package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.constants.MobileTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class AppVersionParam {

	@ApiModelProperty(value = "版本号", required = true)
	private String appVersion;

	@ApiModelProperty(value = "大版本号", required = true)
	private String appBigVersion;

	@ApiModelProperty(value = "更新内容", required = true)
	private String updateDesc;

	@ApiModelProperty(value = "手机类型", required = true)
	private MobileTypeEnum mobileType;

	@ApiModelProperty(value = "app类型", required = true)
	private AppTypeEnum appType;

	@ApiModelProperty(value = "是否强制更新", required = true)
	private Boolean isForce;
	

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppBigVersion() {
		return appBigVersion;
	}

	public void setAppBigVersion(String appBigVersion) {
		this.appBigVersion = appBigVersion;
	}

	public String getUpdateDesc() {
		return updateDesc;
	}


	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public MobileTypeEnum getMobileType() {
		return mobileType;
	}

	public void setMobileType(MobileTypeEnum mobileType) {
		this.mobileType = mobileType;
	}

	public AppTypeEnum getAppType() {
		return appType;
	}

	public void setAppType(AppTypeEnum appType) {
		this.appType = appType;
	}


	public Boolean getIsForce() {
		return isForce;
	}

	public void setIsForce(Boolean isForce) {
		this.isForce = isForce;
	}

	

}
