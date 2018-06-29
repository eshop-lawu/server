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
public enum FreezeStatusEnum {

	FREEZE((byte) 0x00), // 冻结
	RELEASE((byte) 0x01);//释放
	
	private Byte val;

	FreezeStatusEnum(Byte val) {
		this.val = val;
	}

	public static FreezeStatusEnum getEnum(Byte val) {
		FreezeStatusEnum[] values = FreezeStatusEnum.values();
		for (FreezeStatusEnum object : values) {
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
