package com.lawu.eshop.order.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingCartDTO {
	
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    
	/**
	 * 商家ID
	 */
    @ApiModelProperty(value = "商家ID", required = true)
	private Long merchantId;
    
    /**
    * 商家门店id
    */
    @ApiModelProperty(value = "商家门店id", required = true)
    private Long merchantStoreId;
    
    /**
     * 商家名称
     */
    @ApiModelProperty(value = "商家名称", required = true)
    private String merchantName;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long productId;

    /**
    * 商品型号ID
    */
    @ApiModelProperty(value = "商品型号ID", required = true)
    private Long productModelId;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", required = true)
    private Integer quantity;

    /**
     * 现价
     */
    @ApiModelProperty(value = "现价", required = true)
    private BigDecimal salesPrice;

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

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductModelId() {
		return productModelId;
	}

	public void setProductModelId(Long productModelId) {
		this.productModelId = productModelId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}
    
}