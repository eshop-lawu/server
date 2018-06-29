package com.lawu.eshop.statistics.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public class ReportUserActiveAreaDTO {
    @ApiModelProperty(value = "会员数")
    private Integer memberCount;

    @ApiModelProperty(value = "商家数")
    private Integer merchantCount;

    @ApiModelProperty(value = "市级ID")
    private Integer cityId;

    @ApiModelProperty(value = "市级名称")
    private String cityName;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getMerchantCount() {
        return merchantCount;
    }

    public void setMerchantCount(Integer merchantCount) {
        this.merchantCount = merchantCount;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
