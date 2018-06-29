package com.lawu.eshop.product.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class FavoriteProductDTO {
	 @ApiModelProperty(value = "商品id")
	 private Long id;
	
	 @ApiModelProperty(value = "商品名称")
	 private String name;
	 
	 @ApiModelProperty(value = "商品图片")
	 private String featureImage;
	 
	 @ApiModelProperty(value = "现价")
	 private BigDecimal price;
	 
	 @ApiModelProperty(value = "描述")
	 private String content;
	 
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	 
	 

}
