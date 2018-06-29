package com.lawu.eshop.ad.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AdDO implements Serializable {
    /**
     *
     * 主键
     * ad.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 商家ID
     * ad.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     *
     * 商家编号
     * ad.merchant_num
     *
     * @mbg.generated
     */
    private String merchantNum;

    /**
     *
     * 经营类型(1-普通商户2-实体店铺)
     * ad.manage_type
     *
     * @mbg.generated
     */
    private Byte manageType;

    /**
     *
     * 店铺名称
     * ad.merchant_store_name
     *
     * @mbg.generated
     */
    private String merchantStoreName;

    /**
     *
     * 商家门店id
     * ad.merchant_store_id
     *
     * @mbg.generated
     */
    private Long merchantStoreId;

    /**
     *
     * 关联id
     * ad.relate_id
     *
     * @mbg.generated
     */
    private Long relateId;

    /**
     *
     * 关联类型 0没有关联  1 商品 2店铺
     * ad.relate_type
     *
     * @mbg.generated
     */
    private Byte relateType;

    /**
     *
     * 经度
     * ad.merchant_longitude
     *
     * @mbg.generated
     */
    private BigDecimal merchantLongitude;

    /**
     *
     * 纬度
     * ad.merchant_latitude
     *
     * @mbg.generated
     */
    private BigDecimal merchantLatitude;

    /**
     *
     * 商家地区
     * ad.merchant_region_path
     *
     * @mbg.generated
     */
    private String merchantRegionPath;

    /**
     *
     * 名称
     * ad.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * 广附件路径
     * ad.media_url
     *
     * @mbg.generated
     */
    private String mediaUrl;

    /**
     *
     * 文件大小
     * ad.file_size
     *
     * @mbg.generated
     */
    private String fileSize;

    /**
     *
     * 文件时长(视频)
     * ad.file_time
     *
     * @mbg.generated
     */
    private String fileTime;

    /**
     *
     * 文件类型 1-视频 2-图片
     * ad.file_type
     *
     * @mbg.generated
     */
    private Byte fileType;

    /**
     *
     * 门店logo
     * ad.logo_url
     *
     * @mbg.generated
     */
    private String logoUrl;

    /**
     *
     * 视频封面图片路径
     * ad.video_img_url
     *
     * @mbg.generated
     */
    private String videoImgUrl;

    /**
     *
     * 广告内容
     * ad.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     *
     * 广告类型(1-平面广告|2-视频广告|3-E赞|4-红包)
     * ad.type
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     *
     * 投放方式(1-区域2-粉丝 3-雷达)
     * ad.put_way
     *
     * @mbg.generated
     */
    private Byte putWay;

    /**
     *
     * 开始时间
     * ad.begin_time
     *
     * @mbg.generated
     */
    private Date beginTime;

    /**
     *
     * 结束时间
     * ad.end_time
     *
     * @mbg.generated
     */
    private Date endTime;

    /**
     *
     * 投放区域
     * ad.areas
     *
     * @mbg.generated
     */
    private String areas;

    /**
     *
     * 区域名称
     * ad.region_name
     *
     * @mbg.generated
     */
    private String regionName;

    /**
     *
     * 半径，单位米
     * ad.radius
     *
     * @mbg.generated
     */
    private Integer radius;

    /**
     *
     * 性别 (0--男，1--全部，2--女)
     * ad.sex
     *
     * @mbg.generated
     */
    private Byte sex;

    /**
     *
     * 年龄范围（小）
     * ad.min_age
     *
     * @mbg.generated
     */
    private Integer minAge;

    /**
     *
     * 年龄范围（大）
     * ad.max_age
     *
     * @mbg.generated
     */
    private Integer maxAge;

    /**
     *
     * 单个积分
     * ad.point
     *
     * @mbg.generated
     */
    private BigDecimal point;

    /**
     *
     * 总投放积分
     * ad.total_point
     *
     * @mbg.generated
     */
    private BigDecimal totalPoint;

    /**
     *
     * 广告数量
     * ad.ad_count
     *
     * @mbg.generated
     */
    private Integer adCount;

    /**
     *
     * 已点击次数
     * ad.hits
     *
     * @mbg.generated
     */
    private Integer hits;

    /**
     *
     * 广告浏览次数
     * ad.viewCount
     *
     * @mbg.generated
     */
    private Integer viewcount;

    /**
     *
     * 状态(0-删除|1-上架|2-投放中|3-投放结束|4-下架|5-审核中|审核不通过)
     * ad.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * E咻类型 1 拼图 2 点赞
     * ad.praise_type
     *
     * @mbg.generated
     */
    private Byte praiseType;

    /**
     *
     * 广告订单编号
     * ad.ad_order_num
     *
     * @mbg.generated
     */
    private String adOrderNum;

    /**
     *
     * 第三方交易号
     * ad.third_number
     *
     * @mbg.generated
     */
    private String thirdNumber;

    /**
     *
     * 是否支付
     * ad.is_pay
     *
     * @mbg.generated
     */
    private Boolean isPay;

    /**
     *
     * 支付类型
     * ad.pay_type
     *
     * @mbg.generated
     */
    private Byte payType;

    /**
     *
     * 支付客户端类型
     * ad.client_type
     *
     * @mbg.generated
     */
    private Byte clientType;

    /**
     *
     * 审核人员ID
     * ad.auditor_id
     *
     * @mbg.generated
     */
    private Integer auditorId;

    /**
     *
     * 审核备注
     * ad.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * 审核时间
     * ad.audit_time
     *
     * @mbg.generated
     */
    private Date auditTime;

    /**
     *
     * 修改时间
     * ad.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * ad.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ad
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.id
     *
     * @return the value of ad.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.id
     *
     * @param id the value for ad.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.merchant_id
     *
     * @return the value of ad.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.merchant_id
     *
     * @param merchantId the value for ad.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.merchant_num
     *
     * @return the value of ad.merchant_num
     *
     * @mbg.generated
     */
    public String getMerchantNum() {
        return merchantNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.merchant_num
     *
     * @param merchantNum the value for ad.merchant_num
     *
     * @mbg.generated
     */
    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum == null ? null : merchantNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.manage_type
     *
     * @return the value of ad.manage_type
     *
     * @mbg.generated
     */
    public Byte getManageType() {
        return manageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.manage_type
     *
     * @param manageType the value for ad.manage_type
     *
     * @mbg.generated
     */
    public void setManageType(Byte manageType) {
        this.manageType = manageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.merchant_store_name
     *
     * @return the value of ad.merchant_store_name
     *
     * @mbg.generated
     */
    public String getMerchantStoreName() {
        return merchantStoreName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.merchant_store_name
     *
     * @param merchantStoreName the value for ad.merchant_store_name
     *
     * @mbg.generated
     */
    public void setMerchantStoreName(String merchantStoreName) {
        this.merchantStoreName = merchantStoreName == null ? null : merchantStoreName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.merchant_store_id
     *
     * @return the value of ad.merchant_store_id
     *
     * @mbg.generated
     */
    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.merchant_store_id
     *
     * @param merchantStoreId the value for ad.merchant_store_id
     *
     * @mbg.generated
     */
    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.relate_id
     *
     * @return the value of ad.relate_id
     *
     * @mbg.generated
     */
    public Long getRelateId() {
        return relateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.relate_id
     *
     * @param relateId the value for ad.relate_id
     *
     * @mbg.generated
     */
    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.relate_type
     *
     * @return the value of ad.relate_type
     *
     * @mbg.generated
     */
    public Byte getRelateType() {
        return relateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.relate_type
     *
     * @param relateType the value for ad.relate_type
     *
     * @mbg.generated
     */
    public void setRelateType(Byte relateType) {
        this.relateType = relateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.merchant_longitude
     *
     * @return the value of ad.merchant_longitude
     *
     * @mbg.generated
     */
    public BigDecimal getMerchantLongitude() {
        return merchantLongitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.merchant_longitude
     *
     * @param merchantLongitude the value for ad.merchant_longitude
     *
     * @mbg.generated
     */
    public void setMerchantLongitude(BigDecimal merchantLongitude) {
        this.merchantLongitude = merchantLongitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.merchant_latitude
     *
     * @return the value of ad.merchant_latitude
     *
     * @mbg.generated
     */
    public BigDecimal getMerchantLatitude() {
        return merchantLatitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.merchant_latitude
     *
     * @param merchantLatitude the value for ad.merchant_latitude
     *
     * @mbg.generated
     */
    public void setMerchantLatitude(BigDecimal merchantLatitude) {
        this.merchantLatitude = merchantLatitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.merchant_region_path
     *
     * @return the value of ad.merchant_region_path
     *
     * @mbg.generated
     */
    public String getMerchantRegionPath() {
        return merchantRegionPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.merchant_region_path
     *
     * @param merchantRegionPath the value for ad.merchant_region_path
     *
     * @mbg.generated
     */
    public void setMerchantRegionPath(String merchantRegionPath) {
        this.merchantRegionPath = merchantRegionPath == null ? null : merchantRegionPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.title
     *
     * @return the value of ad.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.title
     *
     * @param title the value for ad.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.media_url
     *
     * @return the value of ad.media_url
     *
     * @mbg.generated
     */
    public String getMediaUrl() {
        return mediaUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.media_url
     *
     * @param mediaUrl the value for ad.media_url
     *
     * @mbg.generated
     */
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl == null ? null : mediaUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.file_size
     *
     * @return the value of ad.file_size
     *
     * @mbg.generated
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.file_size
     *
     * @param fileSize the value for ad.file_size
     *
     * @mbg.generated
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.file_time
     *
     * @return the value of ad.file_time
     *
     * @mbg.generated
     */
    public String getFileTime() {
        return fileTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.file_time
     *
     * @param fileTime the value for ad.file_time
     *
     * @mbg.generated
     */
    public void setFileTime(String fileTime) {
        this.fileTime = fileTime == null ? null : fileTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.file_type
     *
     * @return the value of ad.file_type
     *
     * @mbg.generated
     */
    public Byte getFileType() {
        return fileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.file_type
     *
     * @param fileType the value for ad.file_type
     *
     * @mbg.generated
     */
    public void setFileType(Byte fileType) {
        this.fileType = fileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.logo_url
     *
     * @return the value of ad.logo_url
     *
     * @mbg.generated
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.logo_url
     *
     * @param logoUrl the value for ad.logo_url
     *
     * @mbg.generated
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.video_img_url
     *
     * @return the value of ad.video_img_url
     *
     * @mbg.generated
     */
    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.video_img_url
     *
     * @param videoImgUrl the value for ad.video_img_url
     *
     * @mbg.generated
     */
    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl == null ? null : videoImgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.content
     *
     * @return the value of ad.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.content
     *
     * @param content the value for ad.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.type
     *
     * @return the value of ad.type
     *
     * @mbg.generated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.type
     *
     * @param type the value for ad.type
     *
     * @mbg.generated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.put_way
     *
     * @return the value of ad.put_way
     *
     * @mbg.generated
     */
    public Byte getPutWay() {
        return putWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.put_way
     *
     * @param putWay the value for ad.put_way
     *
     * @mbg.generated
     */
    public void setPutWay(Byte putWay) {
        this.putWay = putWay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.begin_time
     *
     * @return the value of ad.begin_time
     *
     * @mbg.generated
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.begin_time
     *
     * @param beginTime the value for ad.begin_time
     *
     * @mbg.generated
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.end_time
     *
     * @return the value of ad.end_time
     *
     * @mbg.generated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.end_time
     *
     * @param endTime the value for ad.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.areas
     *
     * @return the value of ad.areas
     *
     * @mbg.generated
     */
    public String getAreas() {
        return areas;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.areas
     *
     * @param areas the value for ad.areas
     *
     * @mbg.generated
     */
    public void setAreas(String areas) {
        this.areas = areas == null ? null : areas.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.region_name
     *
     * @return the value of ad.region_name
     *
     * @mbg.generated
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.region_name
     *
     * @param regionName the value for ad.region_name
     *
     * @mbg.generated
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.radius
     *
     * @return the value of ad.radius
     *
     * @mbg.generated
     */
    public Integer getRadius() {
        return radius;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.radius
     *
     * @param radius the value for ad.radius
     *
     * @mbg.generated
     */
    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.sex
     *
     * @return the value of ad.sex
     *
     * @mbg.generated
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.sex
     *
     * @param sex the value for ad.sex
     *
     * @mbg.generated
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.min_age
     *
     * @return the value of ad.min_age
     *
     * @mbg.generated
     */
    public Integer getMinAge() {
        return minAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.min_age
     *
     * @param minAge the value for ad.min_age
     *
     * @mbg.generated
     */
    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.max_age
     *
     * @return the value of ad.max_age
     *
     * @mbg.generated
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.max_age
     *
     * @param maxAge the value for ad.max_age
     *
     * @mbg.generated
     */
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.point
     *
     * @return the value of ad.point
     *
     * @mbg.generated
     */
    public BigDecimal getPoint() {
        return point;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.point
     *
     * @param point the value for ad.point
     *
     * @mbg.generated
     */
    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.total_point
     *
     * @return the value of ad.total_point
     *
     * @mbg.generated
     */
    public BigDecimal getTotalPoint() {
        return totalPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.total_point
     *
     * @param totalPoint the value for ad.total_point
     *
     * @mbg.generated
     */
    public void setTotalPoint(BigDecimal totalPoint) {
        this.totalPoint = totalPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.ad_count
     *
     * @return the value of ad.ad_count
     *
     * @mbg.generated
     */
    public Integer getAdCount() {
        return adCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.ad_count
     *
     * @param adCount the value for ad.ad_count
     *
     * @mbg.generated
     */
    public void setAdCount(Integer adCount) {
        this.adCount = adCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.hits
     *
     * @return the value of ad.hits
     *
     * @mbg.generated
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.hits
     *
     * @param hits the value for ad.hits
     *
     * @mbg.generated
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.viewCount
     *
     * @return the value of ad.viewCount
     *
     * @mbg.generated
     */
    public Integer getViewcount() {
        return viewcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.viewCount
     *
     * @param viewcount the value for ad.viewCount
     *
     * @mbg.generated
     */
    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.status
     *
     * @return the value of ad.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.status
     *
     * @param status the value for ad.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.praise_type
     *
     * @return the value of ad.praise_type
     *
     * @mbg.generated
     */
    public Byte getPraiseType() {
        return praiseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.praise_type
     *
     * @param praiseType the value for ad.praise_type
     *
     * @mbg.generated
     */
    public void setPraiseType(Byte praiseType) {
        this.praiseType = praiseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.ad_order_num
     *
     * @return the value of ad.ad_order_num
     *
     * @mbg.generated
     */
    public String getAdOrderNum() {
        return adOrderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.ad_order_num
     *
     * @param adOrderNum the value for ad.ad_order_num
     *
     * @mbg.generated
     */
    public void setAdOrderNum(String adOrderNum) {
        this.adOrderNum = adOrderNum == null ? null : adOrderNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.third_number
     *
     * @return the value of ad.third_number
     *
     * @mbg.generated
     */
    public String getThirdNumber() {
        return thirdNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.third_number
     *
     * @param thirdNumber the value for ad.third_number
     *
     * @mbg.generated
     */
    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber == null ? null : thirdNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.is_pay
     *
     * @return the value of ad.is_pay
     *
     * @mbg.generated
     */
    public Boolean getIsPay() {
        return isPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.is_pay
     *
     * @param isPay the value for ad.is_pay
     *
     * @mbg.generated
     */
    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.pay_type
     *
     * @return the value of ad.pay_type
     *
     * @mbg.generated
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.pay_type
     *
     * @param payType the value for ad.pay_type
     *
     * @mbg.generated
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.client_type
     *
     * @return the value of ad.client_type
     *
     * @mbg.generated
     */
    public Byte getClientType() {
        return clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.client_type
     *
     * @param clientType the value for ad.client_type
     *
     * @mbg.generated
     */
    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.auditor_id
     *
     * @return the value of ad.auditor_id
     *
     * @mbg.generated
     */
    public Integer getAuditorId() {
        return auditorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.auditor_id
     *
     * @param auditorId the value for ad.auditor_id
     *
     * @mbg.generated
     */
    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.remark
     *
     * @return the value of ad.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.remark
     *
     * @param remark the value for ad.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.audit_time
     *
     * @return the value of ad.audit_time
     *
     * @mbg.generated
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.audit_time
     *
     * @param auditTime the value for ad.audit_time
     *
     * @mbg.generated
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.gmt_modified
     *
     * @return the value of ad.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.gmt_modified
     *
     * @param gmtModified the value for ad.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ad.gmt_create
     *
     * @return the value of ad.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ad.gmt_create
     *
     * @param gmtCreate the value for ad.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}