package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;

import io.swagger.annotations.ApiParam;

public class AdPlatformParam {

	@ApiParam(name = "productId", value = "商品id")
	private Long productId;
	
	@ApiParam(name = "merchantStoreId", value = "门店id")
	private Long merchantStoreId;

	@ApiParam(name = "title", required = true, value = "广告标题")
	private String title;

	@ApiParam(name = "linkUrl", value = "链接地址")
	private String linkUrl;

	@ApiParam(name = "typeEnum", required = true, value = "广告类型")
	private TypeEnum typeEnum;
	
	@ApiParam(name = "content", value = "广告内容")
	private String content;
	
	@ApiParam(name = "positionEnum", value = "广告位置 POSITON_RECOMMEND 人气推荐 POSITON_SHOP_TOP 要购物顶部广告 POSITON_SHOP_CHOOSE"
			+ "要购物今日推荐  POSITON_SHOP_GOODS 要购物精品 POSITON_AD_TOP 看广告顶部广告 SHOPPING_BUY "
			+ "E店必够 SHOPPING_GOODS  特色好货  SHOPPING_BENEFIT 实惠单品 SHOPPING_HOT 热门商品 AD_POSITION_TWO 广告模块位置2 AD_POSITION_THREE 广告模块位置2 AD_POSITION_FOUR 广告模块位置3")
	private PositionEnum positionEnum;
	
	@ApiParam(name = "regionPath", value = "区域")
	private String regionPath;
	
	@ApiParam(name = "regionName", value = "区域名称")
	private String regionName;
	
	@ApiParam(name = "mediaUrl", value = "附件路径")
	private String mediaUrl;
	
	@ApiParam(name = "adId", value = "广告id")
	private Long adId;

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

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public TypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(TypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public PositionEnum getPositionEnum() {
		return positionEnum;
	}

	public void setPositionEnum(PositionEnum positionEnum) {
		this.positionEnum = positionEnum;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
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
	
	
	
}
