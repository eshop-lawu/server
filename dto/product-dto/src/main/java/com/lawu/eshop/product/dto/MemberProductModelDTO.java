package com.lawu.eshop.product.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 用户端商品详情页面，选择型号
 * </p>
 * @author Yangqh
 * @date 2017年4月26日 上午10:23:23
 *
 */
public class MemberProductModelDTO {

    @ApiModelProperty(value = "型号ID")
	private Long id;
    
    @ApiModelProperty(value = "型号名称")
    private String name;

    @ApiModelProperty(value = "型号原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "型号现价")
    private BigDecimal price;

    @ApiModelProperty(value = "型号库存")
    private Integer inventory;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	
}
