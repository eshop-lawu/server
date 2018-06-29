package com.lawu.eshop.solr.impl.entity;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName="product")
public class ProductSolr {

    /**
     * 商品id
     */
    @Id 
    private Long id;

    /**
     * 特征图片
     */
    @Field("featureImage_s")
    private String featureImage;

    /**
     * 商品名称
     */
    @Field
    private String name;
    
    /**
     * 平均日销量
     */
    @Field("averageDailySales_d")
    private Double averageDailySales;
    
    /**
     * 商品类型
     */
    @Field("categoryId_i")
    private Integer categoryId;
    
    /**
     * 原价
     */
    @Field("originalPrice_d")
    private Double originalPrice;
    
    /**
     * 现价
     */
    @Field("price_d")
    private Double price;
    
    /**
     * 库存
     */
    @Field("inventory_i")
    private Integer inventory;
    
    /**
     * 销量
     */
    @Field("salesVolume_i")
    private Integer salesVolume;
    
    /**
     * 关键词
     */
    @Field
    private String keywords;
    
    /**
     * 关键词集合
     */
    @Field("keyword_ss")
    private List<String> keywordss;

    /**
     * 品牌id
     */
    @Field("brandId_i")
    private Integer brandId;

    /**
     * 发货地id
     */
    @Field("cityId_i")
    private Integer cityId;

    /**
     * 包邮标记(0--不包邮,1--包邮)
     */
    @Field("expressFree_i")
    private Integer expressFree;

    /**
     * 退换货标记(0--不支持,1--支持)
     */
    @Field("allowRefund_i")
    private Integer allowRefund;

    /**
     * 商品子类目id
     */
    @Field("categorySubitemId_i")
    private Integer categorySubitemId;

    /**
     * 发货地
     */
    @Field("shipmentAddress_s")
    private String shipmentAddress;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Double getAverageDailySales() {
        return averageDailySales;
    }

    public void setAverageDailySales(Double averageDailySales) {
        this.averageDailySales = averageDailySales;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getExpressFree() {
        return expressFree;
    }

    public void setExpressFree(Integer expressFree) {
        this.expressFree = expressFree;
    }

    public Integer getAllowRefund() {
        return allowRefund;
    }

    public void setAllowRefund(Integer allowRefund) {
        this.allowRefund = allowRefund;
    }

    public Integer getCategorySubitemId() {
        return categorySubitemId;
    }

    public void setCategorySubitemId(Integer categorySubitemId) {
        this.categorySubitemId = categorySubitemId;
    }

    public String getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
}