package com.lawu.eshop.user.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date on 2017/8/26.
 */
public class MerchantDetailDTO {

    @ApiModelProperty(name = "name", value = "店铺名称", required = true)
    private String name;

    @ApiModelProperty(name = "regionName", value = "省市区名称", required = true)
    private String regionName;

    @ApiModelProperty(name = "address", value = "店铺地址信息", required = true)
    private String address;

    @ApiModelProperty(name = "industryName", value = "主营业务名称", required = true)
    private String industryName;

    @ApiModelProperty(value = "关键词")
    private String keywords;

    @ApiModelProperty(name = "intro", value = "店铺介绍", required = true)
    private String intro;

    @ApiModelProperty(value = "状态")
    private MerchantStatusEnum statusEnum;

    @ApiModelProperty(name = "principalName", value = "负责人名字", required = true)
    private String principalName;

    @ApiModelProperty(name = "principalMobile", value = "负责人手机", required = true)
    private String principalMobile;

    @ApiModelProperty(name = "companyName", value = "注册公司名称", required = true)
    private String companyName;

    @ApiModelProperty(name = "regNumber", value = "营业执照号码")
    private String regNumber;

    @ApiModelProperty(name = "companyAddress", value = "经营住所")
    private String companyAddress;

    @ApiModelProperty(name = "licenseIndate", value = "营业执照有效期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date licenseIndate;

    @ApiModelProperty(name = "manageType", value = "经营类型", required = true)
    private MerchantStoreTypeEnum manageType;

    @ApiModelProperty(name = "certifType", value = "证件类型", required = true)
    private CertifTypeEnum certifType;

    @ApiModelProperty(name = "operatorCardId", value = "个人经营者身份证号码")
    private String operatorCardId;

    @ApiModelProperty(name = "operatorName", value = "个人经营者姓名")
    private String operatorName;

    @ApiModelProperty(name = "storeUrl", value = "门店照")
    private List<String> storeUrl;

    @ApiModelProperty(name = "environmentUrl", value = "门店环境照")
    private List<String> environmentUrl;

    @ApiModelProperty(name = "idcardUrl", value = "身份证照")
    private List<String> idcardUrl;

    @ApiModelProperty(name = "licenseUrl", value = "营业执照照片")
    private List<String> licenseUrl;

    @ApiModelProperty(name = "logoUrl", value = "门店logo")
    private String logoUrl;

    @ApiModelProperty(name = "otherUrl", value = "其他照片")
    private List<String> otherUrl;
    
    @ApiModelProperty(name = "userNum", value = "商家编号")
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
