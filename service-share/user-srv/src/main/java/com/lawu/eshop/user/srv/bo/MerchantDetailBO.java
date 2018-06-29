package com.lawu.eshop.user.srv.bo;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;

/**
 * @author meishuquan
 * @date 2017/8/26.
 */
public class MerchantDetailBO {

    private String name;

    private String regionName;

    private String address;

    private String industryName;

    private String keywords;

    private String intro;

    private MerchantStatusEnum statusEnum;

    private String principalName;

    private String principalMobile;

    private String companyName;

    private String regNumber;

    private String companyAddress;

    private Date licenseIndate;

    private MerchantStoreTypeEnum manageType;

    private CertifTypeEnum certifType;

    private String operatorCardId;

    private String operatorName;

    private List<String> storeUrl;

    private List<String> environmentUrl;

    private List<String> idcardUrl;

    private List<String> licenseUrl;

    private String logoUrl;

    private List<String> otherUrl;
    
    private String userNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public MerchantStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(MerchantStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalMobile() {
        return principalMobile;
    }

    public void setPrincipalMobile(String principalMobile) {
        this.principalMobile = principalMobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Date getLicenseIndate() {
        return licenseIndate;
    }

    public void setLicenseIndate(Date licenseIndate) {
        this.licenseIndate = licenseIndate;
    }

    public MerchantStoreTypeEnum getManageType() {
        return manageType;
    }

    public void setManageType(MerchantStoreTypeEnum manageType) {
        this.manageType = manageType;
    }

    public CertifTypeEnum getCertifType() {
        return certifType;
    }

    public void setCertifType(CertifTypeEnum certifType) {
        this.certifType = certifType;
    }

    public String getOperatorCardId() {
        return operatorCardId;
    }

    public void setOperatorCardId(String operatorCardId) {
        this.operatorCardId = operatorCardId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public List<String> getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(List<String> storeUrl) {
        this.storeUrl = storeUrl;
    }

    public List<String> getEnvironmentUrl() {
        return environmentUrl;
    }

    public void setEnvironmentUrl(List<String> environmentUrl) {
        this.environmentUrl = environmentUrl;
    }

    public List<String> getIdcardUrl() {
        return idcardUrl;
    }

    public void setIdcardUrl(List<String> idcardUrl) {
        this.idcardUrl = idcardUrl;
    }

    public List<String> getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(List<String> licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<String> getOtherUrl() {
        return otherUrl;
    }

    public void setOtherUrl(List<String> otherUrl) {
        this.otherUrl = otherUrl;
    }

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
    
}
