package com.lawu.eshop.ad.srv.bo;

import java.util.Date;

import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class AdPlatformBO {

	private Long id;

	private Long productId;

	private String title;

	private String mediaUrl;

	private String linkUrl;

	private byte type;

	private String content;

	private Long merchantStoreId;
	
	private PositionEnum positionEnum;
	
	private TypeEnum typeEnum;

	private Date gmtCreate;

	private Byte status;
	
	 private String regionPath;
	 
	 private Long adId;
	 
	 private String regionName;
	 
	private int priority;
	
	public TypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(TypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public PositionEnum getPositionEnum() {
		return positionEnum;
	}

	public void setPositionEnum(PositionEnum positionEnum) {
		this.positionEnum = positionEnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
}
