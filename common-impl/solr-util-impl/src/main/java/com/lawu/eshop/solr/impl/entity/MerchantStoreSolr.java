package com.lawu.eshop.solr.impl.entity;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName="merchant")
public class MerchantStoreSolr {

    /**
     * 店铺id
     */
    @Id 
    private Long id;

    /**
     * 商家id
     */
    @Field("merchantId_l")
    private Long merchantId;
    
    /**
     * 店铺名称
     */
    @Field
    private String name;
    
    /**
     * 地区
     */
    @Field("regionPath_s")
    private String regionPath;
    
    /**
     * 经纬度
     */
    @Field("latLon_p")
    private String latLon;
    
    /**
     * 行业分类
     */
    @Field("industryPath_s")
    private String industryPath;
    
    /**
     * 行业分类名称
     */
    @Field("industryName_s")
    private String industryName;
    
    /**
     * 收藏人数
     */
    @Field("favoriteNumber_i")
    private Integer favoriteNumber;
    
    /**
     * 平均消费金额
     */
    @Field("averageConsumeAmount_d")
    private Double averageConsumeAmount;
    
    /**
     * 平均评分
     */
    @Field("averageScore_d")
    private Double averageScore;
    
    /**
     * 门店照
     */
    @Field("storePic_s")
    private String storePic;
    
    /**
     * 关键字
     */
    @Field
    private String keywords;
    
    /**
     * 关键词集合
     */
    @Field("keyword_ss")
    private List<String> keywordss;
    
    /**
     * 优惠信息
     */
    @Field("favoreInfo_s")
    private String favoreInfo;
    
    /**
     * 优惠结束时间
     */
    @Field("favoreEndTime_s")
    private String favoreEndTime;
    
    /**
     * 优惠套餐
     */
    @Field("discountPackage_s")
    private String discountPackage;
    
    /**
     * 优惠力度
     */
    @Field("discountOrdinal_d")
    private Double discountOrdinal;
    
    /**
     * 距离
     */
    @Field("distance")
    private Double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }
    
    public String getLatLon() {
        return latLon;
    }

    public void setLatLon(String latLon) {
        this.latLon = latLon;
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

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
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
    
    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public List<String> getKeywordss() {
        return keywordss;
    }

    public void setKeywordss(List<String> keywordss) {
        this.keywordss = keywordss;
    }

    public String getFavoreInfo() {
        return favoreInfo;
    }

    public void setFavoreInfo(String favoreInfo) {
        this.favoreInfo = favoreInfo;
    }

    public String getFavoreEndTime() {
        return favoreEndTime;
    }

    public void setFavoreEndTime(String favoreEndTime) {
        this.favoreEndTime = favoreEndTime;
    }

    public String getDiscountPackage() {
        return discountPackage;
    }

    public void setDiscountPackage(String discountPackage) {
        this.discountPackage = discountPackage;
    }

    public Double getDiscountOrdinal() {
        return discountOrdinal;
    }

    public void setDiscountOrdinal(Double discountOrdinal) {
        this.discountOrdinal = discountOrdinal;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}