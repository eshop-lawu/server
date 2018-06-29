package com.lawu.eshop.order.param;

import java.math.BigDecimal;

/**
 * api请求srv参数<p>
 * 保存购物车参数
 * 
 * @author Sunny
 * @date 2017年6月28日
 */
public class ShoppingCartSaveParam {

	/**
	 * 商家ID
	 */
	private Long merchantId;

	/**
	 * 商家门店id
	 */
	private Long merchantStoreId;

	/**
	 * 商家名称
	 */
	private String merchantName;

	/**
	 * 商品ID
	 */
	private Long productId;

	/**
	 * 商品型号ID
	 */
	private Long productModelId;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 现价
	 */
	private BigDecimal salesPrice;

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