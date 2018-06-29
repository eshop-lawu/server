package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;

/**
 * 精选推荐广告BO
 * 
 * @author jiangxinjun
 * @date 2017年7月19日
 */
public class ChoicenessAdBO {
	
    /**
    * 主键
    */
    private Long id;
	
	/**
	 * 商家门店id
	 */
	private Long merchantStoreId;

	/**
	 * 店铺名称
	 */
	private String merchantStoreName;

	/**
	 * 经营类型
	 */
	private ManageTypeEnum manageType;

	/**
	 * 名称 ad.title
	 */
	private String title;

	/**
	 * 门店logo
	 */
	private String logoUrl;

	/**
	 * 广附件路径
	 */
	private String mediaUrl;

	/**
	 * 视频封面图片路径
	 */
	private String videoImgUrl;

	/**
	 * 广告内容 ad.content
	 */
	private String content;

	/**
	 * 广告类型
	 */
	private AdEgainTypeEnum type;

	/**
	 * 广告浏览次数
	 */
	private Integer viewcount;

	/**
	 * 是否收藏
	 */
	private Boolean isFavorite;
	
	/**
	 * 投放开始时间
	 */
    private Date beginTime;
	
	/**
	 * 总积分
	 */
    private BigDecimal totalPoint;
	
	/**
	 * 抢赞状态
	 */
	private AdPraiseStatusEnum status;
	
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

}