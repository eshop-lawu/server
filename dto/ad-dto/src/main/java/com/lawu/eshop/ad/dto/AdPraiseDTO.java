package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class AdPraiseDTO {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "广告标题")
	private String title;

	@ApiModelProperty(value = "广告投放开始时间")
	private Date beginTime;

	@ApiModelProperty(value = "广告总积分")
	private BigDecimal totalPoint;

	@ApiModelProperty(value = "商铺名称")
	private String name;

	@ApiModelProperty(value = "抢赞人数")
	private Integer count;
	
	@ApiModelProperty(value = "店铺id")
	private Long  merchantStoreId;
	
	@ApiModelProperty(name = "logoUrl", value = "logo图片路径")
    private String logoUrl;
	
	@ApiModelProperty(value = "开始倒计时")
	private Long needBeginTime;
	
	@ApiModelProperty(value = "是否收藏")
	private Boolean isFavorite;
	
	@ApiModelProperty(value = "广告附件路径")
	private String mediaUrl;
	
	@ApiModelProperty(value = "是否抢赞")
	private Boolean isPraise;
	
	@ApiModelProperty(value = "ENTITY 实体  COMMON 普通")
	private ManageTypeEnum manageTypeEnum;
	
	@ApiModelProperty(value = "商家id")
	private Long merchantId;
	
	@ApiModelProperty(value = "是否扣除积分")
	private Boolean isDoHanlderMinusPoint;
	
	@ApiModelProperty(value = "假次数")
	private Integer clickPraiseAdTimes;
	
	@ApiModelProperty(value = "概率数")
	private Integer praiseProb;
	
	@ApiModelProperty(value = "内容")
	private String content;
	
	@ApiModelProperty(value = "提示语")
	private List<String> words;
	
	@ApiModelProperty (name="relateId", value = "关联id")
	private Long relateId;
	
	@ApiModelProperty (name="relateType", value = "PRODUCT_TYPE 商品  | MERCHANT_STORE_TYPE 店铺")
	private RelateTypeEnum relateType;
	
	@ApiModelProperty(value = "AD_STATUS_DELETE 删除 AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中  AD_STATUS_PUTED 投放结束"
			+ "AD_STATUS_OUT 下架 AD_STATUS_AUDIT 审核中")
	private AdStatusEnum statusEnum;
	
	@ApiModelProperty (name="praiseType", value = "PRAISE_TYPE_PUZZLE 拼图  | PRAISE_TYPE_CLICK 点赞")
	private PraiseTypeEnum praiseType;
	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public Long getNeedBeginTime() {
		return needBeginTime;
	}

	public void setNeedBeginTime(Long needBeginTime) {
		this.needBeginTime = needBeginTime;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public Boolean getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(Boolean isPraise) {
		this.isPraise = isPraise;
	}

	public ManageTypeEnum getManageTypeEnum() {
		return manageTypeEnum;
	}

	public void setManageTypeEnum(ManageTypeEnum manageTypeEnum) {
		this.manageTypeEnum = manageTypeEnum;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Boolean getIsDoHanlderMinusPoint() {
		return isDoHanlderMinusPoint;
	}

	public void setIsDoHanlderMinusPoint(Boolean isDoHanlderMinusPoint) {
		this.isDoHanlderMinusPoint = isDoHanlderMinusPoint;
	}

	public Integer getClickPraiseAdTimes() {
		return clickPraiseAdTimes;
	}

	public void setClickPraiseAdTimes(Integer clickPraiseAdTimes) {
		this.clickPraiseAdTimes = clickPraiseAdTimes;
	}

	public Integer getPraiseProb() {
		return praiseProb;
	}

	public void setPraiseProb(Integer praiseProb) {
		this.praiseProb = praiseProb;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
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

	
	
	
}

