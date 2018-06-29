package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 冻结资金业务类型
 * </p>
 * @author Yangqh
 * @date 2017年4月14日 上午10:31:09
 *
 */
public enum FreezeTypeEnum {

	PRODUCT_ORDER((byte) 0x01); // 商品订单
	private Byte val;

	FreezeTypeEnum(Byte val) {
		this.val = val;
	}

	public static FreezeTypeEnum getEnum(Byte val) {
		FreezeTypeEnum[] values = FreezeTypeEnum.values();
		for (FreezeTypeEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public Byte getVal() {
		return val;
	}
	
}
