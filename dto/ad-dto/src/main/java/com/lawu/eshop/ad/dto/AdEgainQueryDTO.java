package com.lawu.eshop.ad.dto;

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询E赚广告DTO
 * @author jiangxinjun
 * @date 2017年7月18日
 */
@ApiModel
public class AdEgainQueryDTO {
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private Long id;

	/**
	 * 商家门店id
	 */
	@ApiModelProperty(value = "商家门店id", required = true)
	private Long merchantStoreId;

	/**
	 * 店铺名称
	 */
	@ApiModelProperty(value = "店铺名称", required = true)
	private String merchantStoreName;

	/**
	 * 经营类型
	 */
	@ApiModelProperty(value = "经营类型(COMMON-普通|ENTITY-实体)", required = true)
	private ManageTypeEnum manageType;

	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", required = true)
	private String title;

	/**
	 * 门店logo
	 */
	@ApiModelProperty(value = "门店logo", required = true)
	private String logoUrl;

	/**
	 * 广告附件路径
	 */
	@ApiModelProperty(value = "广告附件路径", required = true)
	private String mediaUrl;

	/**
	 * 视频封面图片路径
	 */
	@ApiModelProperty(value = "视频封面图片路径", required = true)
	private String videoImgUrl;

	/**
	 * 广告内容
	 */
	@ApiModelProperty(value = "广告内容", required = true)
	private String content;

	/**
	 * 广告类型
	 */
	@ApiModelProperty(value = "广告类型(AD_TYPE_FLAT-平面|AD_TYPE_VIDEO-视频)", required = true)
	private AdEgainTypeEnum type;

	/**
	 * 广告浏览次数
	 */
	@ApiModelProperty(value = "广告浏览次数", required = true)
	private Integer viewcount;

	/**
	 * 是否收藏
	 */
	@ApiModelProperty(value = "是否收藏", required = true)
	private Boolean isFavorite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AdEgainTypeEnum getType() {
		return type;
	}

	public void setType(AdEgainTypeEnum type) {
		this.type = type;
	}

	public Integer getViewcount() {
		return viewcount;
	}

	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	
}
