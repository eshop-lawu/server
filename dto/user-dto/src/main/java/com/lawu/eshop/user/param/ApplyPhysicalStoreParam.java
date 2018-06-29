package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/7/20.
 */
public class ApplyPhysicalStoreParam {
    /**
     * 门店照
     */
    @ApiModelProperty(name = "storeUrl", value = "门店照")
    private String storeUrl;
    /**
     * 门店环境照
     */
    @ApiModelProperty(name = "environmentUrl", value = "门店环境照")
    private String environmentUrl;
    /**
     * 门店logo
     */
    @ApiModelProperty(name = "logoUrl", value = "门店logo")
    private String logoUrl;

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
