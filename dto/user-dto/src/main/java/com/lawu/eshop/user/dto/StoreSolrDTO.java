package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/5.
 */
public class StoreSolrDTO {

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "门店ID")
    private Long merchantStoreId;

    @ApiModelProperty(value = "门店名称")
    private String name;

    @ApiModelProperty(value = "主营业务")
    private String industryPath;

    @ApiModelProperty(value = "主营业务名称")
    private String industryName;

    @ApiModelProperty(value = "门店照")
    private String storePic;

    @ApiModelProperty(value = "距离,单位km")
    private Double distance;

    @ApiModelProperty(value = "收藏数")
    private Integer favoriteNumber;

    @ApiModelProperty(value = "人均消费")
    private Double averageConsumeAmount;

    @ApiModelProperty(value = "平均评分")
    private Double averageScore;

    @ApiModelProperty(value = "区域路径")
    private String regionPath;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "优惠信息")
    private String favoreInfo;

    @ApiModelProperty(value = "优惠套餐")
    private String discountPackage;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustryPath() {
        return industryPath;
    }

    public void setIndustryPath(String industryPath) {
        this.industryPath = industryPath;
    }

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public Double getAverageConsumeAmount() {
        return averageConsumeAmount;
    }

    public void setAverageConsumeAmount(Double averageConsumeAmount) {
        this.averageConsumeAmount = averageConsumeAmount;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
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
}
