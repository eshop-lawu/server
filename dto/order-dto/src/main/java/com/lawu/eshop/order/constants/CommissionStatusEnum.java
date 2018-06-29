package com.lawu.eshop.order.constants;

public enum CommissionStatusEnum {

	// 提成状态(0-没有计算过|1-计算过)
	
	/**
	 * 0-没有计算过
	 */
	NOT_COUNTED((byte)0x00),
	
	 
	/**
	 * 1-计算过
	 */
	CALCULATED((byte)0x01);

	private Byte value;
	
	CommissionStatusEnum(Byte value) {
		this.value = value;
	}
	
	public Byte getValue() {
		return value;
	}

	public static CommissionStatusEnum getEnum(Byte value){
		for (CommissionStatusEnum item : CommissionStatusEnum.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}
}
