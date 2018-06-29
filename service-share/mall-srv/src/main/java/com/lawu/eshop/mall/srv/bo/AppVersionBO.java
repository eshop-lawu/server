package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

public class AppVersionBO {
	

    private String appVersion;

    private String appBigVersion;

    private String updateDesc;

    private byte status;

    private Date gmtCreate;
    
    private Boolean isForce;

	public Boolean getIsForce() {
		return isForce;
	}

	public void setIsForce(Boolean isForce) {
		this.isForce = isForce;
	}

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

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
}
