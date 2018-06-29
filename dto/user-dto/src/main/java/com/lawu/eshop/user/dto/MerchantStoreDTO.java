package com.lawu.eshop.user.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class MerchantStoreDTO {


    @ApiModelProperty(name = "merchantStoreId", value = "门店id")
    private Long merchantStoreId;
    /**
     * 店铺名称
     * merchant_store.name
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    @ApiModelProperty(name = "name", value = "店铺名称")
    private String name;

    /**
     * 省市区
     * merchant_store.region_path
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    @ApiModelProperty(name = "regionPath", value = "省市区")
    private String regionPath;

    /**
     * 店铺地址信息
     * merchant_store.address
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    @ApiModelProperty(name = "address", value = "店铺地址信息")
    private String address;

    /**
     * 经度
     * merchant_store.longitude
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    @ApiModelProperty(name = "longitude", value = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     * merchant_store.latitude
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    @ApiModelProperty(name = "latitude", value = "纬度")
    private BigDecimal latitude;

    /**
     * 主营业务
     * merchant_store.industry_path
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    @ApiModelProperty(name = "industryPath", value = "主营业务")
    private String industryPath;

    /**
     * 店铺介绍
     * merchant_store.intro
     *
     * @mbg.generated 2017-03-24 10:29:55
     */
    @ApiModelProperty(name = "intro", value = "店铺介绍")
    private String intro;

    /**
     * 负责人
     */
    @ApiModelProperty(name = "principalName", value = "负责人")
    private String principalName;

    /**
     * 负责人电话
     */
    @ApiModelProperty(name = "principalMobile", value = "负责人电话")
    private String principalMobile;

    /**
     * 注册公司名称
     */
    @ApiModelProperty(name = "companyName", value = "注册公司名称")
    private String companyName;

    /**
     * 营业执照号码
     */
    @ApiModelProperty(name = "regNumber", value = "营业执照号码")
    private String regNumber;

    /**
     * 经营住所
     */
    @ApiModelProperty(name = "companyAddress", value = "经营住所")
    private String companyAddress;

    /**
     * 营业执照有效期
     */
    @ApiModelProperty(name = "licenseIndate", value = "营业执照有效期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date licenseIndate;

    /**
     * 经营类型
     */
    @ApiModelProperty(name = "manageType", value = "经营类型")
    private MerchantStoreTypeEnum manageType;

    /**
     * 证件类型
     */
    @ApiModelProperty(name = "certifType", value = "证件类型")
    private CertifTypeEnum certifType;

    /**
     * 个人经营者身份证号码
     */
    @ApiModelProperty(name = "operatorCardId", value = "个人经营者身份证号码")
    private String operatorCardId;

    /**
     * 个人经营者姓名
     */
    @ApiModelProperty(name = "operatorName", value = "个人经营者姓名")
    private String operatorName;


    /**
     * logo图片路径
     */
    @ApiModelProperty(name = "logoUrl", value = "logo图片路径")
    private String logoUrl;

    @ApiModelProperty(name = "environmentUrl", value = "门店环境图片路径")
    private String environmentUrl;

    @ApiModelProperty(name = "otherUrl", value = "其他证件图片路径")
    private String otherUrl;

    @ApiModelProperty(name = "idcardUrl", value = "身份证图片路径")
    private String idcardUrl;

    @ApiModelProperty(name = "licenseUrl", value = "执照图片路径")
    private String licenseUrl;

    @ApiModelProperty(name = "storeUrl", value = "门店图片路径")
    private String storeUrl;

    @ApiModelProperty(name = "regionName", value = "区域名称")
    private String regionName;
    @ApiModelProperty(name = "industryName", value = "主营业务")
    private String industryName;

    /**
     * 门店状态
     */
    @ApiModelProperty(name = "merchantStatus", value = "门店状态:MERCHANT_STATUS_UNCHECK:未审核,MERCHANT_STATUS_CHECKED:审核通过,MERCHANT_STATUS_CHECK_FAILED:审核不通过,MERCHANT_STATUS_NOT_MONEY:未提交保证金,MERCHANT_STATUS_GIVE_MONEY_CHECK:已提交保证金待财务核实,MERCHANT_STATUS_GIVE_MONEY_CHECK_FAILED:财务审核不通过")
    private MerchantStatusEnum merchantStatus;

    /**
     * 门店审核状态
     */
    @ApiModelProperty(name = "auditSuccess", value = "门店审核状态 false未审核，true 已审核/审核")
    private boolean auditSuccess = false;

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "关键词")
    private String keywords;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

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

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getIndustryPath() {
        return industryPath;
    }

    public void setIndustryPath(String industryPath) {
        this.industryPath = industryPath;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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


    public MerchantStatusEnum getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(MerchantStatusEnum merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public boolean isAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    public void setEnvironmentUrl(String environmentUrl) {
        this.environmentUrl = environmentUrl;
    }

    public String getOtherUrl() {
        return otherUrl;
    }

    public void setOtherUrl(String otherUrl) {
        this.otherUrl = otherUrl;
    }

    public String getIdcardUrl() {
        return idcardUrl;
    }

    public void setIdcardUrl(String idcardUrl) {
        this.idcardUrl = idcardUrl;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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
}