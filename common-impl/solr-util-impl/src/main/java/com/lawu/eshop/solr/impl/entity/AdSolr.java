package com.lawu.eshop.solr.impl.entity;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName="ad")
public class AdSolr {
    
    /**
     * 广告id
     */
    @Field
    private Long id;
    
    /**
     * 商家id
     */
    @Field("merchantId_l")
    private Long merchantId;
    
    /**
     * 广告图片
     */
    @Field("mediaUrl_s")
    private String mediaUrl;
    
    /**
     * 视频路径
     */
    @Field("videoImgUrl_s")
    private String videoImgUrl;
    
    /**
     * 广告标题
     */
    @Field("title_s")
    private String title;
    
    /**
     * 广告内容
     */
    @Field("content_s")
    private String content;
    
    /**
     * 广告剩余点击次数
     */
    @Field("hits_i")
    private Integer hits;
    
    /**
     * 广告总点击次数
     */
    @Field("adCount_i")
    private Integer adCount;
    
    /**
     * 浏览次数
     */
    @Field("viewCount_i")
    private Integer viewCount;
    
    /**
     * 广告状态
     */
    @Field("status_i")
    private Byte status;
    
    /**
     * 广告类型
     */
    @Field("type_i")
    private Byte type;
    
    /**
     * 广告投放方式
     */
    @Field("putWay_i")
    private Byte putWay;
    
    /**
     * 单价
     */
    @Field("point_d")
    private Double point;
    
    /**
     * 总积分
     */
    @Field("totalPoint_d")
    private Double totalPoint;
    
    /**
     * 店铺名称
     */
    @Field("storeName_s")
    private String storeName;
    
    /**
     * 门店Logo
     */
    @Field("storeLogo_s")
    private String storeLogo;
    
    /**
     * E咻类型
     */
    @Field("praiseType_i")
    private Byte praiseType;
    
    /**
     * 标记次数
     */
    @Field("adMark_i")
    private Integer adMark;
    
    /**
     * 广告投放经纬度
     */
    @Field("latLon_p")
    private String latLon;
    
    /**
     * 广告投放区域
     */
    @Field("area_is")
    private List<Integer> area;
    
    /**
     * 距离
     */
    @Field("distance")
    private Double distance;
    
    /**
     * 投放半径
     */
    @Field("radius_i")
    private Integer radius;
    
    /**
     * 开始时间
     */
    @Field("beginTime_l")
    private Long beginTime;

    /**
     * 性别(0--男，2--女，1--全部)
     */
    @Field("sex_i")
    private Integer sex;

    /**
     * 最小年龄
     */
    @Field("minAge_i")
    private Integer minAge;

    /**
     * 最大年龄
     */
    @Field("maxAge_i")
    private Integer maxAge;

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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getAdCount() {
        return adCount;
    }

    public void setAdCount(Integer adCount) {
        this.adCount = adCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getPutWay() {
        return putWay;
    }

    public void setPutWay(Byte putWay) {
        this.putWay = putWay;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public Byte getPraiseType() {
        return praiseType;
    }

    public void setPraiseType(Byte praiseType) {
        this.praiseType = praiseType;
    }

    public Integer getAdMark() {
        return adMark;
    }

    public void setAdMark(Integer adMark) {
        this.adMark = adMark;
    }

    public String getLatLon() {
        return latLon;
    }

    public void setLatLon(String latLon) {
        this.latLon = latLon;
    }
    
    public List<Integer> getArea() {
        return area;
    }

    public void setArea(List<Integer> area) {
        this.area = area;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
    
    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}