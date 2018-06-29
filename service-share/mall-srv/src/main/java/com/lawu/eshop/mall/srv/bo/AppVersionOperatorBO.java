package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.constants.MobileTypeEnum;

public class AppVersionOperatorBO {
	
	private Integer id;
	
    private String appVersion;

    private String appBigVersion;

    private String updateDesc;

    private AppStatusEnum statusEnum;

    private Date gmtCreate;
    
    private Boolean isForce;
    
    private AppTypeEnum appType;
    
    private MobileTypeEnum mobileType;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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


	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public AppTypeEnum getAppType() {
		return appType;
	}

	public void setAppType(AppTypeEnum appType) {
		this.appType = appType;
	}

	public MobileTypeEnum getMobileType() {
		return mobileType;
	}

	public void setMobileType(MobileTypeEnum mobileType) {
		this.mobileType = mobileType;
	}

	public AppStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(AppStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	
	
	
}
