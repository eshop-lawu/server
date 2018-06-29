package com.lawu.eshop.user.param;

import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
public class ApplyStoreParam {
    /**
     * logo图片路径
     */
    @ApiModelProperty(name = "logoUrl", value = "logo图片路径")
    private String logoUrl;

    @ApiModelProperty(name = "environmentUrl", value = "门店环境图片路径")
    private String environmentUrl;

    @ApiModelProperty(name = "storeUrl", value = "门店图片路径")
    private String storeUrl;

    private String name;

    private String principalName;

    private String principalMobile;

    private String industryPath;

    private String industryName;

    private String address;

    private String intro;

    private String regionPath;

    private String regionName;

    private MerchantStoreTypeEnum manageType;

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

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIndustryPath() {
        return industryPath;
    }

    public void setIndustryPath(String industryPath) {
        this.industryPath = industryPath;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public MerchantStoreTypeEnum getManageType() {
        return manageType;
    }

    public void setManageType(MerchantStoreTypeEnum manageType) {
        this.manageType = manageType;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
