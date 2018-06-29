package com.lawu.eshop.product.dto;

import java.math.BigDecimal;

import com.lawu.eshop.product.constant.SeckillActivityProductEnum;

import io.swagger.annotations.ApiModelProperty;

public class SeckillActivityProductManageDTO {
	
	@ApiModelProperty(value = "商品id")
	private Long productId;
	
	@ApiModelProperty(value = "商品图片")
	private String productPicture;
	
	@ApiModelProperty(value = "商品名称")
	private String productName;
	
	@ApiModelProperty(value = "销量")
	private Integer saleCount;
	
	@ApiModelProperty(value = "成交额")
	private BigDecimal saleMoney;
	
	@ApiModelProperty(value = "总量")
	private Integer modelCount;
	
	@ApiModelProperty(value = "UNAUDIT 未审核 AUDIT 已审核 FAIL 未通过")
	private SeckillActivityProductEnum  statusEnum;
	
	@ApiModelProperty(value = "审核原因")
	private String reasons;

	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(String productPicture) {
		this.productPicture = productPicture;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public BigDecimal getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}

	public Integer getModelCount() {
		return modelCount;
	}

	public void setModelCount(Integer modelCount) {
		this.modelCount = modelCount;
	}

	public SeckillActivityProductEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(SeckillActivityProductEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	
	
	
	

}
