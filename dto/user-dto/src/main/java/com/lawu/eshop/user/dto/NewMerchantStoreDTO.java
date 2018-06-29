package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/7/28.
 */
public class NewMerchantStoreDTO {

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "门店ID")
    private Long merchantStoreId;

    @ApiModelProperty(value = "门店名称")
    private String name;

    @ApiModelProperty(value = "行业名称")
    private String industryName;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "区域路径")
    private String regionPath;

    @ApiModelProperty(value = "地区名称")
    private String areaName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "门店照")
    private String storePic;

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

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
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

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
