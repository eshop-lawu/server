package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.product.constant.ActivityProductStatusEnum;
import com.lawu.eshop.product.constant.SeckillActivityProductEnum;

public class SeckillActivityProductManageBO {
	
	private Long productId;
	
	private String productPicture;
	
	private String productName;
	
	private BigDecimal saleMoney;
	
	private Integer modelCount;
	
	private Integer saleCount;
	
	private SeckillActivityProductEnum  statusEnum;
	
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

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
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
