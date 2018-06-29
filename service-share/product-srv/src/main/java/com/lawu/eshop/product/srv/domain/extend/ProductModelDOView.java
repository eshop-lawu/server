package com.lawu.eshop.product.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductModelDOView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 318188197730190698L;

	private Long id;

    private Long merchantId;

    private Long productId;

    private String num;

    private String name;

    private BigDecimal originalPrice;

    private BigDecimal price;

    private Integer salesVolume;

    private Integer inventory;

    private Boolean status;

    private Date gmtModified;

    private Date gmtCreate;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

    
}