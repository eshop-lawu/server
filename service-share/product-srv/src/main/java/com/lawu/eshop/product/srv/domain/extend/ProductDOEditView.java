package com.lawu.eshop.product.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年6月27日 下午2:24:57
 *
 */
public class ProductDOEditView {

	 private Long id;
	 
	 private Integer totalInventory;
	 
	 private Integer totalSalesVolume;
	 
	 private BigDecimal minPrice;

	 private BigDecimal maxPrice;
	 
	 private String flag;//添加或修改商品操作总库存时用于判断是新增还是修改

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}

	public Integer getTotalSalesVolume() {
		return totalSalesVolume;
	}

	public void setTotalSalesVolume(Integer totalSalesVolume) {
		this.totalSalesVolume = totalSalesVolume;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	 
	 
	 
}
