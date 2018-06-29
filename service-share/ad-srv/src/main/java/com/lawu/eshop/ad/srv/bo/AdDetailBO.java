package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.FileTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;

public class AdDetailBO {

	private Long id;

	private String title;

	private String mediaUrl;

	private String content;

	private AdTypeEnum typeEnum;

	private PutWayEnum putWayEnum;

	private Integer radius;

	private BigDecimal point;

	private BigDecimal totalPoint;

	private Integer adCount;

	private AdStatusEnum statusEnum;

	private Date gmtCreate;

	private String videoImgUrl;

	private String regionName;

	private BigDecimal alreadyGetPoint;

	private BigDecimal notGetPoint;

	private Integer alreadyGetCount;

	private Integer notGetCount;
	
	private String remark;
	
	private Date auditTime;
	
	private Date beginTime;
	
	private Long productId;
	
	private String redPacketAdFileUrl;
	
	private FileTypeEnum fileType;
	
	private String storeName;
	
	private UserSexEnum sexEnum;
	
	private Integer minAge;
	
	private Integer maxAge;
	
	private Boolean isCanShare;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getAlreadyGetPoint() {
		return alreadyGetPoint;
	}

	public void setAlreadyGetPoint(BigDecimal alreadyGetPoint) {
		this.alreadyGetPoint = alreadyGetPoint;
	}

	public BigDecimal getNotGetPoint() {
		return notGetPoint;
	}

	public void setNotGetPoint(BigDecimal notGetPoint) {
		this.notGetPoint = notGetPoint;
	}

	public Integer getAlreadyGetCount() {
		return alreadyGetCount;
	}

	public void setAlreadyGetCount(Integer alreadyGetCount) {
		this.alreadyGetCount = alreadyGetCount;
	}

	public Integer getNotGetCount() {
		return notGetCount;
	}

	public void setNotGetCount(Integer notGetCount) {
		this.notGetCount = notGetCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getRedPacketAdFileUrl() {
		return redPacketAdFileUrl;
	}

	public void setRedPacketAdFileUrl(String redPacketAdFileUrl) {
		this.redPacketAdFileUrl = redPacketAdFileUrl;
	}

	public FileTypeEnum getFileType() {
		return fileType;
	}

	public void setFileType(FileTypeEnum fileType) {
		this.fileType = fileType;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public Boolean getIsCanShare() {
		return isCanShare;
	}

	public void setIsCanShare(Boolean isCanShare) {
		this.isCanShare = isCanShare;
	}

	
	
	
}