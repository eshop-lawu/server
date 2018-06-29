package com.lawu.eshop.order.constants;

public enum ShoppingOrderStatusEnum {

	// 订单状态(0-待处理|1-待付款|2-待发货|3-待收货|4-交易成功|5-交易关闭|6-退款中)
	
	/**
	 * 0-待处理
	 */
	PENDING((byte) 0x00),
	
	/**
	 * 1-待付款
	 */
	PENDING_PAYMENT((byte) 0x01),

	/**
	 * 2-待发货
	 */
	BE_SHIPPED((byte) 0x02),
	
	/**
	 * 3-待收货
	 */
	TO_BE_RECEIVED((byte) 0x03),

	/**
	 * 4-交易成功
	 */
	TRADING_SUCCESS((byte) 0x04),

	/**
	 * 5-交易关闭
	 */
	CANCEL_TRANSACTION((byte) 0x05),

	/**
	 * 6-退款中
	 */
	REFUNDING((byte) 0x06);

	private Byte value;
	
	ShoppingOrderStatusEnum(Byte value) {
		this.value = value;
	}
	
	public Byte getValue() {
		return value;
	}

	public static ShoppingOrderStatusEnum getEnum(Byte value){
		for (ShoppingOrderStatusEnum item : ShoppingOrderStatusEnum.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}
}
