package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;

public class AdBO {

	private Long id;

	private Long merchantId;

	private String title;

	private String mediaUrl;

	private String content;

	private AdTypeEnum typeEnum;

	private PutWayEnum putWayEnum;

	private Date beginTime;

	private String areas;

	private Integer radius;

	private BigDecimal point;

	private BigDecimal totalPoint;

	private Integer adCount;

	private AdStatusEnum statusEnum;

	private Date gmtCreate;

	private Integer viewCount;

	private Integer number;

	private Boolean isFavorite;

	private Boolean isPraise;

	private AdPraiseStatusEnum adPraiseStatusEnum;

	private Integer auditorId;

	private String remark;

	/**
	 *
	 * 经度 ad.merchant_longitude
	 *
	 * @mbg.generated
	 */
	private BigDecimal merchantLongitude;

	/**
	 *
	 * 纬度 ad.merchant_latitude
	 *
	 * @mbg.generated
	 */
	private BigDecimal merchantLatitude;

	private String videoImgUrl;

	private String regionName;

	private Boolean isClickAd;

	private Long relateId;

	private RelateTypeEnum relateType;
	
	private Long merchantStoreId;
	 
	private String merchantStoreName;
	 
	private ManageTypeEnum manageType;
	 
	private String logoUrl;
	
	private String fileSize;
	
	private String videoTime;

	private String merchantRegionPath;
	
	private PraiseTypeEnum praiseType;

	private String adOrderNum;
	
	private UserSexEnum sexEnum;
	
	private Integer minAge;
	
	private Integer maxAge;

	public PraiseTypeEnum getPraiseType() {
		return praiseType;
	}

	public void setPraiseType(PraiseTypeEnum praiseType) {
		this.praiseType = praiseType;
	}

	public Boolean getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(Boolean isPraise) {
		this.isPraise = isPraise;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AdTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(AdTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public PutWayEnum getPutWayEnum() {
		return putWayEnum;
	}

	public void setPutWayEnum(PutWayEnum putWayEnum) {
		this.putWayEnum = putWayEnum;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getAdCount() {
		return adCount;
	}

	public void setAdCount(Integer adCount) {
		this.adCount = adCount;
	}

	public AdStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(AdStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public AdPraiseStatusEnum getAdPraiseStatusEnum() {
		return adPraiseStatusEnum;
	}

	public void setAdPraiseStatusEnum(AdPraiseStatusEnum adPraiseStatusEnum) {
		this.adPraiseStatusEnum = adPraiseStatusEnum;
	}

	public BigDecimal getMerchantLongitude() {
		return merchantLongitude;
	}

	public void setMerchantLongitude(BigDecimal merchantLongitude) {
		this.merchantLongitude = merchantLongitude;
	}

	public BigDecimal getMerchantLatitude() {
		return merchantLatitude;
	}

	public void setMerchantLatitude(BigDecimal merchantLatitude) {
		this.merchantLatitude = merchantLatitude;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsClickAd() {
		return isClickAd;
	}

	public void setIsClickAd(Boolean isClickAd) {
		this.isClickAd = isClickAd;
	}

	public Long getRelateId() {
		return relateId;
	}

	public void setRelateId(Long relateId) {
		this.relateId = relateId;
	}

	public RelateTypeEnum getRelateType() {
		return relateType;
	}

	public void setRelateType(RelateTypeEnum relateType) {
		this.relateType = relateType;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getMerchantStoreName() {
		return merchantStoreName;
	}

	public void setMerchantStoreName(String merchantStoreName) {
		this.merchantStoreName = merchantStoreName;
	}

	public ManageTypeEnum getManageType() {
		return manageType;
	}

	public void setManageType(ManageTypeEnum manageType) {
		this.manageType = manageType;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public String getMerchantRegionPath() {
		return merchantRegionPath;
	}

	public void setMerchantRegionPath(String merchantRegionPath) {
		this.merchantRegionPath = merchantRegionPath;
	}

	public String getAdOrderNum() {
		return adOrderNum;
	}

	public void setAdOrderNum(String adOrderNum) {
		this.adOrderNum = adOrderNum;
	}

	public UserSexEnum getSexEnum() {
		return sexEnum;
	}

	public void setSexEnum(UserSexEnum sexEnum) {
		this.sexEnum = sexEnum;
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