package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;

import io.swagger.annotations.ApiModelProperty;


public class AdMerchantDTO {
	
	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "商家id")
    private Long merchantId;

	@ApiModelProperty(value = "广告标题")
    private String title;

	@ApiModelProperty(value = "广告类型(AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞 AD_TYPE_PACKET 红包)")
    private AdTypeEnum typeEnum;

	@ApiModelProperty(value = "投放方式(PUT_WAY_AREAS 区域 PUT_WAY_FENS 粉丝 PUT_WAY_RADAR 雷达 PUT_WAY_COMMON 普通红包 PUT_WAY_LUCK 手气红包)")
    private PutWayEnum putWayEnum;

	@ApiModelProperty(value = "总积分")
    private BigDecimal totalPoint;

	@ApiModelProperty(value = "AD_STATUS_DELETE 删除 AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中  AD_STATUS_PUTED 投放结束"
			+ "AD_STATUS_OUT 下架 AD_STATUS_AUDIT 审核中")
    private AdStatusEnum statusEnum;

	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;
	
	@ApiModelProperty(value = "推广人数")
	private Integer memberCount;
	
	@ApiModelProperty(value = "推广单号")
	private String expandOrder;
	
	@ApiModelProperty(value = "广告附件路径")
    private String mediaUrl;
	
	@ApiModelProperty(value = "视频封面图片路径")
	private String videoImgUrl;
	
	@ApiModelProperty(value = "雷达半径")
    private Integer radius;
	
	@ApiModelProperty(value = "区域名称")
	private String regionName;
	
	@ApiModelProperty(value = "广告个数")
	private Integer adCount;
	
	@ApiModelProperty(value = "是否显示下架按钮")
	private Boolean isShowDown;

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

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public String getExpandOrder() {
		return expandOrder;
	}

	public void setExpandOrder(String expandOrder) {
		this.expandOrder = expandOrder;
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

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getAdCount() {
		return adCount;
	}

	public void setAdCount(Integer adCount) {
		this.adCount = adCount;
	}

	public Boolean getIsShowDown() {
		return isShowDown;
	}

	public void setIsShowDown(Boolean isShowDown) {
		this.isShowDown = isShowDown;
	}

	

	
	
}
