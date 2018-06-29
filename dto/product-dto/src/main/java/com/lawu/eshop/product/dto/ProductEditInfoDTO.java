package com.lawu.eshop.product.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 商家端修改商品DTO
 * </p>
 * @author Yangqh
 * @date 2017年4月17日 下午7:13:05
 *
 */
public class ProductEditInfoDTO {

	@ApiModelProperty(value = "商品ID")
	private Long id;
	
	@ApiModelProperty(value = "商家ID")
    private Long merchantId;
	
	@ApiModelProperty(value = "商品分类ID")
    private Long category;

	@ApiModelProperty(value = "商品分类全称")
	private String categoryName;
	
	@ApiModelProperty(value = "商品名称")
    private String name;
	
	@ApiModelProperty(value = "商品描述")
    private String content;
	
	@ApiModelProperty(value = "商品特征图片")
    private String featureImage;
	
	@ApiModelProperty(value = "商品描述图片")
    private List<String> imagesUrl;
	
	@ApiModelProperty(value = "商品型号,修改时inventoryTrans、salesVolume透传不做修改")
    private List<ProductModelDTO> spec;
	
	@ApiModelProperty(value = "商品详情")
    private List<String> imageContent;
	
	@ApiModelProperty(value = "商品详情图片")
    private List<String> imageDetailUrl;
	
	@ApiModelProperty(value = "是否支持退换货")
    private boolean isAllowRefund;

	@ApiModelProperty(value = "关键词")
	private String keywords;
	
	public Long getMerchantId() {
		return merchantId;
	}
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFeatureImage() {
		return featureImage;
	}
	public void setFeatureImage(String featureImage) {
		this.featureImage = featureImage;
	}
	public List<String> getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(List<String> imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public List<ProductModelDTO> getSpec() {
		return spec;
	}
	public void setSpec(List<ProductModelDTO> spec) {
		this.spec = spec;
	}
	public boolean isAllowRefund() {
		return isAllowRefund;
	}
	public void setAllowRefund(boolean isAllowRefund) {
		this.isAllowRefund = isAllowRefund;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<String> getImageContent() {
		return imageContent;
	}
	public void setImageContent(List<String> imageContent) {
		this.imageContent = imageContent;
	}
	public List<String> getImageDetailUrl() {
		return imageDetailUrl;
	}
	public void setImageDetailUrl(List<String> imageDetailUrl) {
		this.imageDetailUrl = imageDetailUrl;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
