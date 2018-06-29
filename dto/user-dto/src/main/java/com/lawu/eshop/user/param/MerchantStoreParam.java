package com.lawu.eshop.user.param;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;

import io.swagger.annotations.ApiParam;

/**
 *
 * Created by zhangyong on 2017/3/27.
 */
public class MerchantStoreParam {


    /**
     * 店铺名称
     */
    @ApiParam(name = "name", value = "店铺名称",required = true)
    private String name;

    /**
     * 省市区
     */
    @ApiParam(name = "regionPath", value = "省市区",required = true)
    private String regionPath;

    /**
     * 省市区
     */
    @ApiParam(name = "regionName", value = "省市区名称",required = true)
    private String regionName;

    /**
     * 店铺地址信息
     */
    @ApiParam(name = "address", value = "店铺地址信息")
    private String address;

    /**
     * 经度
     */
    @ApiParam(name = "longitude", value = "经度")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @ApiParam(name = "latitude", value = "纬度")
    private BigDecimal latitude;

    /**
     * 主营业务
     */
    @ApiParam(name = "industryPath", value = "主营业务",required = true)
    private String industryPath;

    /**
     * 主营业务
     */
    @ApiParam(name = "industryName", value = "主营业务名称",required = true)
    private String industryName;

    /**
     * 店铺介绍
     */
    @ApiParam(name = "intro", value = "店铺介绍",required = true)
    private String intro;

    /**
     * 负责人名字
     */
    @ApiParam(name = "principalName", value = "负责人名字",required = true)
    private String principalName;

    /**
     * 负责人手机
     */
    @ApiParam(name = "principalMobile", value = "负责人手机",required = true)
    private String principalMobile;

    /**
     * 注册公司名称
     */
    @ApiParam(name = "companyName", value = "注册公司名称")
    private String companyName;

    /**
     * 营业执照号码
     */
    @ApiParam(name = "regNumber", value = "营业执照号码")
    private String regNumber;

    /**
     * 经营住所
     */
    @ApiParam(name = "companyAddress", value = "经营住所")
    private String companyAddress;

    /**
     * 营业执照有效期
     */
    @ApiParam(name = "licenseIndate", value = "营业执照有效期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseIndate;

    /**
     * 经营类型
     */
    @ApiParam(name = "manageType", value = "经营类型",required = true)
    private MerchantStoreTypeEnum manageType;

    /**
     * 证件类型
     */
    @ApiParam(name = "certifType", value = "证件类型",required = true)
    private CertifTypeEnum certifType;

    /**
     * 个人经营者身份证号码
     */
    @ApiParam(name = "operatorCardId", value = "个人经营者身份证号码")
    private String operatorCardId;

    /**
     * 个人经营者姓名
     */
    @ApiParam(name = "operatorName", value = "个人经营者姓名")
    private String operatorName;

    /**
     * 门店照
     */
    @ApiParam(name = "storeUrl", value = "门店照")
    private String storeUrl;
    /**
     * 门店环境照
     */
    @ApiParam(name = "environmentUrl", value = "门店环境照")
    private String environmentUrl;
    /**
     * 身份证照
     */
    @ApiParam(name = "idcardUrl", value = "身份证照")
    private String idcardUrl;
    /**
     * 营业执照照片
     */
    @ApiParam(name = "licenseUrl", value = "营业执照照片")
    private String licenseUrl;
    /**
     * 门店logo
     */
    @ApiParam(name = "logoUrl", value = "门店logo")
    private String logoUrl;
    /**
     * 其他照片
     */
    @ApiParam(name = "otherUrl", value = "其他照片")
    private String otherUrl;

    @ApiParam(name = "keywords", value = "关键词")
    private String keywords;

    @ApiParam(name = "merchantStoreStatus", value = "门店状态")
    private MerchantStatusEnum merchantStoreStatus;


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

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getEnvironmentUrl() {
        return environmentUrl;
    }

    public void setEnvironmentUrl(String environmentUrl) {
        this.environmentUrl = environmentUrl;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getOtherUrl() {
        return otherUrl;
    }

    public void setOtherUrl(String otherUrl) {
        this.otherUrl = otherUrl;
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

    public MerchantStatusEnum getMerchantStoreStatus() {
        return merchantStoreStatus;
    }

    public void setMerchantStoreStatus(MerchantStatusEnum merchantStoreStatus) {
        this.merchantStoreStatus = merchantStoreStatus;
    }
}
