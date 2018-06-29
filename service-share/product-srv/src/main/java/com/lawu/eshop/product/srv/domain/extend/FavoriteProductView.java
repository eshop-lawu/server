package com.lawu.eshop.product.srv.domain.extend;

import java.math.BigDecimal;

public class FavoriteProductView {
	private Long memberId;
	
	private Long productId;
	
	private String name;
	 
	private String featureImage;
	 
	private BigDecimal price;
	 
	private String content;
	

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeatureImage() {
		return featureImage;
	}

	public void setFeatureImage(String featureImage) {
		this.featureImage = featureImage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
