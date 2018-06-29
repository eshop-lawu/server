package com.lawu.eshop.user.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/7/28.
 */
public class RecommendFoodDTO {

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

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "门店照")
    private String storePic;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "平均得分")
    private BigDecimal averageScore;

    @ApiModelProperty(value = "平均消费")
    private BigDecimal averageConsumeAmount;

    @ApiModelProperty(value = "消费人数")
    private Integer buyNumbers;

    @ApiModelProperty(value = "评价人数")
    private Integer commentsCount;

    @ApiModelProperty(value = "距离,单位km")
    private Double distance;

    @ApiModelProperty(value = "地区名称")
    private String areaName;

    @ApiModelProperty(value = "优惠信息")
    private String favoreInfo;

    @ApiModelProperty(value = "优惠套餐")
    private String discountPackage;

    @ApiModelProperty(value = "优惠有效期")
    private String favoreEndTime;

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

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public BigDecimal getAverageConsumeAmount() {
        return averageConsumeAmount;
    }

    public void setAverageConsumeAmount(BigDecimal averageConsumeAmount) {
        this.averageConsumeAmount = averageConsumeAmount;
    }

    public Integer getBuyNumbers() {
        return buyNumbers;
    }

    public void setBuyNumbers(Integer buyNumbers) {
        this.buyNumbers = buyNumbers;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getFavoreInfo() {
        return favoreInfo;
    }

    public void setFavoreInfo(String favoreInfo) {
        this.favoreInfo = favoreInfo;
    }

    public String getDiscountPackage() {
        return discountPackage;
    }

    public void setDiscountPackage(String discountPackage) {
        this.discountPackage = discountPackage;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getFavoreEndTime() {
        return favoreEndTime;
    }

    public void setFavoreEndTime(String favoreEndTime) {
        this.favoreEndTime = favoreEndTime;
    }
}
