package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/6/15.
 */
public class StoreDetailDOView implements Serializable {
    private static final long serialVersionUID = -5627070018710504476L;

    private Long merchantId;

    private String name;

    private String intro;

    private String principalMobile;

    private Integer favoriteNumber;

    private Integer buyNumbers;

    private BigDecimal averageConsumeAmount;

    private BigDecimal averageScore;

    private BigDecimal feedbackRate;

    private String path;

    private String userNum;

    private String address;

    private byte type;

    private String regionName;

    private String regionPath;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String industryPath;

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPrincipalMobile() {
        return principalMobile;
    }

    public void setPrincipalMobile(String principalMobile) {
        this.principalMobile = principalMobile;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

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

    public String getIndustryPath() {
        return industryPath;
    }

    public void setIndustryPath(String industryPath) {
        this.industryPath = industryPath;
    }
}
