package com.lawu.eshop.ad.dto;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class AdSolrDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商家门店id", required = true)
    private Long merchantStoreId;

    @ApiModelProperty(value = "广告附件路径")
    private String mediaUrl;

    @ApiModelProperty(value = "视频封面图片路径", required = true)
    private String videoImgUrl;

    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "广告内容")
    private String content;

    @ApiModelProperty(value = "广告类型(AD_TYPE_FLAT-平面|AD_TYPE_VIDEO-视频|AD_TYPE_PRAISE-抢赞)", required = true)
    private AdTypeEnum typeEnum;

    @ApiModelProperty(value = "状态")
    private AdStatusEnum statusEnum;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String merchantStoreName;

    @ApiModelProperty(value = "门店logo", required = true)
    private String logoUrl;

    @ApiModelProperty(value = "点击次数")
    private Integer hits;

    @ApiModelProperty(value = "单价")
    private Double point;

    @ApiModelProperty(value = "总积分")
    private Double totalPoint;

    @ApiModelProperty(value = "浏览次数")
    private Integer count;
    
    @ApiModelProperty(value = "E咻类型")
    private PraiseTypeEnum praiseType;
    
    @ApiModelProperty(value = "开始时间")
	private Long BeginTime;
    
    private Boolean isPraise;
    
    private Integer adCount;

    private Integer adMark;

    private Long merchantId;

    public Integer getAdCount() {
		return adCount;
	}

	public void setAdCount(Integer adCount) {
		this.adCount = adCount;
	}

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

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public AdTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(AdTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getMerchantStoreName() {
        return merchantStoreName;
    }

    public void setMerchantStoreName(String merchantStoreName) {
        this.merchantStoreName = merchantStoreName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public AdStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(AdStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

	public PraiseTypeEnum getPraiseType() {
		return praiseType;
	}

	public void setPraiseType(PraiseTypeEnum praiseType) {
		this.praiseType = praiseType;
	}

	public Long getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(Long beginTime) {
		BeginTime = beginTime;
	}

	public Boolean getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(Boolean isPraise) {
		this.isPraise = isPraise;
	}

    public Integer getAdMark() {
        return adMark;
    }

    public void setAdMark(Integer adMark) {
        this.adMark = adMark;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
