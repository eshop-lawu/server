package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;

public class FavoriteProductBO {
	
	 private Long id;
	
	 private String name;
	 
	 private String featureImage;
	 
	 private BigDecimal price;
	 
	 private String content;
	 
	 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	 
	 

}
