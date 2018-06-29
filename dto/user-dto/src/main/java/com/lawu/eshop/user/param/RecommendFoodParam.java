package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2017/7/31.
 */
public class RecommendFoodParam {

    @ApiModelProperty(value = "区域", required = true)
    private String regionPath;

    @ApiModelProperty(value = "经度", required = true)
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度", required = true)
    private BigDecimal latitude;

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
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
}
