package com.lawu.eshop.ad.dto;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.FavoriteTypeEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class FavoriteAdDOViewDTO {

	@ApiModelProperty(value = "主键")
	private Long id;
	
	@ApiModelProperty(value = "广告id")
	private Long AdId;
	
	@ApiModelProperty(value = "商家id")
	private Long merchantId;
	
	@ApiModelProperty(value = "广告标题")
	private String title;
	
	@ApiModelProperty(value = "广告附件路径")
	private String mediaUrl;
	
	@ApiModelProperty(value = "广告内容")
	private String content;
	
	@ApiModelProperty(value = "店铺logo")
	private String logoUrl;
	
	@ApiModelProperty(value = "店铺名称")
	private String name;
	
	@ApiModelProperty(value = "AD_STATUS_DELETE 删除 AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中 AD_STATUS_PUTED 投放结束 AD_STATUS_OUT 下架")
	private AdStatusEnum  statusEnum;
	
	@ApiModelProperty(value = "AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频")
	private FavoriteTypeEnum typeEnum;
	
	@ApiModelProperty(value = "视频封面图片")
	private String videoImgUrl;
	
	@ApiModelProperty(value = "是否抢赞")
	private Boolean isPraise;
	
	@ApiModelProperty (name="praiseType", value = "PRAISE_TYPE_PUZZLE 拼图  | PRAISE_TYPE_CLICK 点赞")
	private PraiseTypeEnum praiseType;
	
	public PraiseTypeEnum getPraiseType() {
		return praiseType;
	}

	public void setPraiseType(PraiseTypeEnum praiseType) {
		this.praiseType = praiseType;
	}

	public AdStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(AdStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdId() {
		return AdId;
	}

	public void setAdId(Long adId) {
		AdId = adId;
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

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FavoriteTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(FavoriteTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}

	public Boolean getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(Boolean isPraise) {
		this.isPraise = isPraise;
	}
	
	

}
