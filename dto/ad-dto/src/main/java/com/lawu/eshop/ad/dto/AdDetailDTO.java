package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.FileTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;

import io.swagger.annotations.ApiModelProperty;


public class AdDetailDTO {
	
	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "广告标题")
    private String title;

	@ApiModelProperty(value = "广告类型(AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞 AD_TYPE_PACKET 红包)")
    private AdTypeEnum typeEnum;

	@ApiModelProperty(value = "投放方式(PUT_WAY_AREAS 区域 PUT_WAY_FENS 粉丝 PUT_WAY_RADAR 雷达 PUT_WAY_COMMON 普通红包 PUT_WAY_LUCK 手气红包)")
    private PutWayEnum putWayEnum;

	@ApiModelProperty(value = "总积分")
    private BigDecimal totalPoint;
	
	@ApiModelProperty(value = "广告单价")
    private BigDecimal point;

	@ApiModelProperty(value = "AD_STATUS_DELETE 删除 AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中  AD_STATUS_PUTED 投放结束"
			+ "AD_STATUS_OUT 下架 AD_STATUS_AUDIT 审核中")
    private AdStatusEnum statusEnum;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date gmtCreate;
	
	@ApiModelProperty(value = "广告数量")
	private Integer adCount;
	
	@ApiModelProperty(value = "广告附件路径")
    private String mediaUrl;
	
	@ApiModelProperty(value = "雷达半径")
	private Integer radius;
	
	@ApiModelProperty(value = "广告内容")
	private String content;
	
	@ApiModelProperty(value = "视频封面图片路径")
	private String videoImgUrl;
	
	@ApiModelProperty(value = "区域名称")
	private String regionName;
	
	@ApiModelProperty(value = "已领积分")
	private BigDecimal alreadyGetPoint;

	@ApiModelProperty(value = "可领积分")
	private BigDecimal notGetPoint;

	@ApiModelProperty(value = "已领数量")
	private Integer alreadyGetCount;

	@ApiModelProperty(value = "可领数量")
	private Integer notGetCount;
	
	@ApiModelProperty(value = "审核原因")
	private String remark;
	
	@ApiModelProperty(value = "审核时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date auditTime;
	
	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date beginTime;
	
	@ApiModelProperty(value = "商品id")
	private Long  productId;
	
	@ApiModelProperty(value = "商品图片")
	private String  productImgUrl;
	
	@ApiModelProperty(value = "商品名称")
	private String  productName;
	
	@ApiModelProperty(value = "红包关联广告文件路径")
	private String redPacketAdFileUrl;
	
	@ApiModelProperty(value = "文件类型")
	private FileTypeEnum fileType;
	
	@ApiModelProperty(value = "店铺名称")
	private String storeName;
	
	@ApiModelProperty(value = "性别 SEX_MALE 男 SEX_SECRET 全部  SEX_FEMALE 女")
	private UserSexEnum sexEnum;
	
	@ApiModelProperty(value = "年龄小")
	private Integer minAge;
	
	@ApiModelProperty(value = "年龄大")
	private Integer maxAge;

	@ApiModelProperty(value = "是否能分享")
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

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
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

	public Integer getAdCount() {
		return adCount;
	}

	public void setAdCount(Integer adCount) {
		this.adCount = adCount;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
