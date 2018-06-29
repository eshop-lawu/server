package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;

import io.swagger.annotations.ApiModelProperty;


public class AdMerchantDetailDTO {
	
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;
	
	@ApiModelProperty(value = "广告数量")
	private Integer adCount;
	
	@ApiModelProperty(value = "区域")
	private String areas;
	
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
	
	@ApiModelProperty(value = "商品id")
	private Long  productId;
	
	@ApiModelProperty(value = "关联类型")
	private RelateTypeEnum relateType;
	
	@ApiModelProperty(value = "商品图片")
	private String  productImgUrl;
	
	@ApiModelProperty(value = "商品名称")
	private String  productName;
	
	@ApiModelProperty(value = "视频大小")
	private String fileSize;
	
	@ApiModelProperty(value = "视频时间")
	private String videoTime;
	
	@ApiModelProperty(value = "E咻类型")
	private PraiseTypeEnum praiseType;
	
	private UserSexEnum sexEnum;
	
	private Integer minAge;
	
	private Integer maxAge;

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

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getAdCount() {
		return adCount;
	}

	public void setAdCount(Integer adCount) {
		this.adCount = adCount;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public RelateTypeEnum getRelateType() {
		return relateType;
	}

	public void setRelateType(RelateTypeEnum relateType) {
		this.relateType = relateType;
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

	public PraiseTypeEnum getPraiseType() {
		return praiseType;
	}

	public void setPraiseType(PraiseTypeEnum praiseType) {
		this.praiseType = praiseType;
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
