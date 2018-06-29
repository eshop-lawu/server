package com.lawu.eshop.mq.dto.product;

/**
 * 检查是否扣除库存成功
 * 
 * @author jiangxinjun
 * @date 2017年7月11日
 */
public enum CheckLessInventoryResultEnum {
	
	/**
	 * 商品已经失效
	 */
	PRODUCT_HAS_EXPIRED("商品已经失效"),
	
	/**
	 * 库存不足
	 */
	INVENTORY_SHORTAGE("库存不足");
	
	private String description;

	private CheckLessInventoryResultEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
