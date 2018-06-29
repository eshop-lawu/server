package com.lawu.eshop.product.srv.bo;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年3月27日 下午8:31:44
 *
 */
public class ProductCategoryDataBO {
	private String name;
	private String originalPrice;
	private String price;
	private String inventory;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
}
