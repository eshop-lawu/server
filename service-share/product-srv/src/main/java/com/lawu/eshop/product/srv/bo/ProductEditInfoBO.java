package com.lawu.eshop.product.srv.bo;

import java.util.List;

/**
 * 产品类别BO
 *
 * @author Yangqh
 * @date 2017/3/22
 */
public class ProductEditInfoBO {

    private Long id;
    private Long merchantId;
    private Long category;
    private String categoryName;
    private String name;
    private String content;
    private String featureImage;
    private List<String> imagesUrl;
    private List<ProductModelBO> spec;
    private List<String> imageContent;
    private List<String> imageDetailUrl;
    private boolean isAllowRefund;
    private String keywords;
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
	
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
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
	public List<String> getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(List<String> imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public List<ProductModelBO> getSpec() {
		return spec;
	}
	public void setSpec(List<ProductModelBO> spec) {
		this.spec = spec;
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
