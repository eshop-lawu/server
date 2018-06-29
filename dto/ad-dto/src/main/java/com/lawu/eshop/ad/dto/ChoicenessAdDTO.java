package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 精选推荐广告DTO
 * @author jiangxinjun
 * @date 2017年7月19日
 */
@ApiModel
public class ChoicenessAdDTO {
	
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
	
	/**
	 * 投放开始时间
	 */
	@ApiModelProperty(value = "投放开始时间", required = true)
	@JsonFormat
    private Date beginTime;
	
	/**
	 * 总积分
	 */
	@ApiModelProperty(value = "总积分", required = true)
    private BigDecimal totalPoint;
	
	/**
	 * 抢赞状态
	 */
	@ApiModelProperty(value = "抢赞状态(AD_STATUS_SHOOT-开枪中 |AD_STATUS_TOBEGIN-即将开始|AD_STATUS_END-已结束)", required = true)
	private AdPraiseStatusEnum status;
	
	/**
	 * 倒计时(距离投放开始或者距离投放结束)
	 */
	@ApiModelProperty(value = "倒计时(距离投放开始或者距离投放结束)")
	private Long needBeginTime;
	
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

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

	public AdPraiseStatusEnum getStatus() {
		return status;
	}

	public void setStatus(AdPraiseStatusEnum status) {
		this.status = status;
	}

	public Long getNeedBeginTime() {
		return needBeginTime;
	}

	public void setNeedBeginTime(Long needBeginTime) {
		this.needBeginTime = needBeginTime;
	}
	
}
