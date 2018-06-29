package com.lawu.eshop.product.constant;

/**
 * 商品库存流水类型
 * 
 * @author Sunny
 * @date 2017年4月13日
 */
public enum ProductModelInventoryTypeEnum {

	/**
	 * 加库存
	 */
	PLUS((byte)0x01),
	
	/**
	 * 减库存
	 */
	MINUS((byte)0x02),
	
	/**
	 * 创建订单
	 */
	CREATE_ORDER((byte)0x03),
	
	/**
	 * 取消订单
	 */
	CANCEL_ORDER((byte)0x04),
	
	/**
     * 创建抢购活动订单
     */
    CREATE_SECKILL_ACTIVITY_ORDER((byte)0x05),
    
    /**
     * 取消抢购活动订单
     */
    CANCEL_SECKILL_ACTIVITY_ORDER((byte)0x06);
	
	private Byte value;

	ProductModelInventoryTypeEnum (Byte value){
		this.value = value;
	}
	
	public Byte getValue() {
		return value;
	}

	public ProductModelInventoryTypeEnum getEnum (Byte value) {
		
		if (value == null) {
			return null;
		}
		
		for (ProductModelInventoryTypeEnum item : ProductModelInventoryTypeEnum.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}
	
}
