package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;

public class AdPraiseBO {
	
	private Long id;

	private String title;

	private Date beginTime;

	private BigDecimal totalPoint;

	private String name;

	private Integer number;
	
	private Long  merchantStoreId;
	
    private String logoUrl;
	
	private Long needBeginTime;
	
	private Boolean isFavorite;
	
	private String mediaUrl;
	
	private Boolean isPraise;
	
	private ManageTypeEnum manageTypeEnum;
	
	private Long merchantId;
	
	private String content;
	
	private AdStatusEnum statusEnum;
	
	private Long relateId;
	
	private RelateTypeEnum relateType;
	
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	

}
