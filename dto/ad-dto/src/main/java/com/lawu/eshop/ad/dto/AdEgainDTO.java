package com.lawu.eshop.ad.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.framework.web.json.XssDecodeJsonSerializer;

import io.swagger.annotations.ApiModelProperty;


public class AdEgainDTO {
	
	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "商家id")
    private Long merchantId;

	@ApiModelProperty(value = "广告标题")
    private String title;

	@ApiModelProperty(value = "广告附件路径")
    private String mediaUrl;

	@ApiModelProperty(value = "广告内容")
    private String content;

	@ApiModelProperty(value = "状态")
    private AdStatusEnum statusEnum;
    
	@ApiModelProperty(value = "浏览数量")
    private Integer viewCount;
    
	@ApiModelProperty(value = "店铺名称")
    private String name;
    
	@ApiModelProperty(value = "店铺id")
    private Long  merchantStoreId;
    
	@ApiModelProperty(name = "logoUrl", value = "logo图片路径")
    private String logoUrl;
	
	@ApiModelProperty(value = "是否收藏")
	private Boolean isFavorite;
	
	@ApiModelProperty(value = "官网链接")
	@JsonSerialize(using = XssDecodeJsonSerializer.class)
	private String websiteUrl;
	 
	@ApiModelProperty(value = "淘宝链接")
	@JsonSerialize(using = XssDecodeJsonSerializer.class)
	private String taobaoUrl;
	
	@ApiModelProperty(value = "天猫链接")
	@JsonSerialize(using = XssDecodeJsonSerializer.class)
	private String tmallUrl;
	
	@ApiModelProperty(value = "京东链接")
	@JsonSerialize(using = XssDecodeJsonSerializer.class)
	private String jdUrl;
	
	@ApiModelProperty(value = "ENTITY 实体  COMMON 普通")
	private ManageTypeEnum manageTypeEnum;
	
	@ApiModelProperty(value = "视频封面图片路径")
	private String videoImgUrl;
	
	@ApiModelProperty(value = "是否点击过广告")
	private Boolean isClickAd;
	
	@ApiModelProperty (name="relateId", value = "关联id")
	private Long relateId;
	
	@ApiModelProperty (name="relateType", value = "PRODUCT_TYPE 商品  | MERCHANT_STORE_TYPE 店铺")
	private RelateTypeEnum relateType;
	
	@ApiModelProperty(value = "视频大小")
	private Double fileSize;
	
	@ApiModelProperty(value = "视频时间")
	private String videoTime;
	
	@ApiModelProperty(value = "广告是否点完")
	private Boolean isClickOver;

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

	public AdStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(AdStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getTaobaoUrl() {
		return taobaoUrl;
	}

	public void setTaobaoUrl(String taobaoUrl) {
		this.taobaoUrl = taobaoUrl;
	}

	public String getTmallUrl() {
		return tmallUrl;
	}

	public void setTmallUrl(String tmallUrl) {
		this.tmallUrl = tmallUrl;
	}

	public String getJdUrl() {
		return jdUrl;
	}

	public void setJdUrl(String jdUrl) {
		this.jdUrl = jdUrl;
	}

	public ManageTypeEnum getManageTypeEnum() {
		return manageTypeEnum;
	}

	public void setManageTypeEnum(ManageTypeEnum manageTypeEnum) {
		this.manageTypeEnum = manageTypeEnum;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
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

	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public Boolean getIsClickOver() {
		return isClickOver;
	}

	public void setIsClickOver(Boolean isClickOver) {
		this.isClickOver = isClickOver;
	}
	
	
}
