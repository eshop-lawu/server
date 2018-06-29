package com.lawu.eshop.statistics.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2017/6/30.
 */
public class ReportUserRegAreaDTO {

    @ApiModelProperty(value = "会员数")
    private Integer memberCount;

    @ApiModelProperty(value = "商家数")
    private Integer merchantCount;

    @ApiModelProperty(value = "普通商家数")
    private Integer merchantCommonCount;

    @ApiModelProperty(value = "实体商家数")
    private Integer merchantEntityCount;

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

    public Integer getMerchantCommonCount() {
        return merchantCommonCount;
    }

    public void setMerchantCommonCount(Integer merchantCommonCount) {
        this.merchantCommonCount = merchantCommonCount;
    }

    public Integer getMerchantEntityCount() {
        return merchantEntityCount;
    }

    public void setMerchantEntityCount(Integer merchantEntityCount) {
        this.merchantEntityCount = merchantEntityCount;
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
