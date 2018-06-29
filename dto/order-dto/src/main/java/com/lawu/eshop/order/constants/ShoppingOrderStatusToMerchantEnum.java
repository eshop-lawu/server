package com.lawu.eshop.order.constants;

public enum ShoppingOrderStatusToMerchantEnum {

	/**
	 * 1-待付款|2-待发货|3-待收货|6-退款中<p>进行中
	 */
	PROCESSING((byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x06),

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
	 * 6-退款中
	 */
	REFUNDING((byte) 0x06),

	/**
	 * 4-已完成
	 */
	COMPLETED((byte) 0x04),

	/**
	 * 5-已关闭
	 */
	CLOSED((byte) 0x05);

	private Byte[] value;

	ShoppingOrderStatusToMerchantEnum(Byte... value) {
		this.value = value;
	}

	public Byte[] getValue() {
		return value;
	}

}
