package com.lawu.eshop.user.dto;

import java.math.BigDecimal;

import com.lawu.eshop.user.constants.MerchantFavoredTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
public class StoreDetailDTO {

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "商家编号")
    private String userNum;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "区域路径")
    private String regionPath;

    @ApiModelProperty(value = "地区名称")
    private String areaName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "负责人手机号")
    private String principalMobile;

    @ApiModelProperty(value = "门店照")
    private String storePic;
    
    /**
     * 门店Logo
     */
    @ApiModelProperty(value = "门店Logo", required = true)
    private String storeLogo;

    @ApiModelProperty(value = "环境照数量")
    private Integer picCount;

    @ApiModelProperty(value = "店铺介绍")
    private String intro;

    @ApiModelProperty(value = "收藏人数")
    private Integer favoriteNumber;

    @ApiModelProperty(value = "是否收藏：true--已收藏，false--未收藏")
    private Boolean isFavorite;

    @ApiModelProperty(value = "人均消费")
    private BigDecimal averageConsumeAmount;

    @ApiModelProperty(value = "平均评分")
    private BigDecimal averageScore;

    @ApiModelProperty(value = "好评率")
    private BigDecimal feedbackRate;

    @ApiModelProperty(value = "每周优惠时间")
    private String preferentialTime;

    @ApiModelProperty(value = "每天优惠时间")
    private String validDayTime;

    @ApiModelProperty(value = "有效时间")
    private String validTime;

    @ApiModelProperty(value = "已买笔数")
    private Integer buyNumbers;

    @ApiModelProperty(value = "优惠信息ID")
    private Long merchantFavoredId;

    @ApiModelProperty(value = "TYPE_FULL：每满，TYPE_FULL_REDUCE：满减，TYPE_DISCOUNT：折扣")
    private MerchantFavoredTypeEnum typeEnum;

    @ApiModelProperty(value = "满额")
    private BigDecimal reachAmount;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal favoredAmount;

    @ApiModelProperty(value = "折扣率")
    private BigDecimal discountRate;

    @ApiModelProperty(value = "是否逾期：true--是，false--否")
    private Boolean isOverdue ;

    @ApiModelProperty(value = "门店是否存在（注销状态） true:是,false:否")
    private boolean isExistStore;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "门店行业")
    private String industryPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrincipalMobile() {
        return principalMobile;
    }

    public void setPrincipalMobile(String principalMobile) {
        this.principalMobile = principalMobile;
    }

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPreferentialTime() {
        return preferentialTime;
    }

    public void setPreferentialTime(String preferentialTime) {
        this.preferentialTime = preferentialTime;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public void setAverageConsumeAmount(BigDecimal averageConsumeAmount) {
        this.averageConsumeAmount = averageConsumeAmount;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public BigDecimal getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(BigDecimal feedbackRate) {
        this.feedbackRate = feedbackRate;
    }

    public Integer getBuyNumbers() {
        return buyNumbers;
    }

    public void setBuyNumbers(Integer buyNumbers) {
        this.buyNumbers = buyNumbers;
    }

    public BigDecimal getAverageConsumeAmount() {
        return averageConsumeAmount;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public Integer getPicCount() {
        return picCount;
    }

    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Long getMerchantFavoredId() {
        return merchantFavoredId;
    }

    public void setMerchantFavoredId(Long merchantFavoredId) {
        this.merchantFavoredId = merchantFavoredId;
    }

    public MerchantFavoredTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(MerchantFavoredTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BigDecimal getReachAmount() {
        return reachAmount;
    }

    public void setReachAmount(BigDecimal reachAmount) {
        this.reachAmount = reachAmount;
    }

    public BigDecimal getFavoredAmount() {
        return favoredAmount;
    }

    public void setFavoredAmount(BigDecimal favoredAmount) {
        this.favoredAmount = favoredAmount;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getValidDayTime() {
        return validDayTime;
    }

    public void setValidDayTime(String validDayTime) {
        this.validDayTime = validDayTime;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Boolean getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Boolean overdue) {
        isOverdue = overdue;
    }

    public boolean getIsExistStore() {
        return isExistStore;
    }

    public void setIsExistStore(boolean existStore) {
        isExistStore = existStore;
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
}
