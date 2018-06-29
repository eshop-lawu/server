package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 提现后台管理操作枚举
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月11日 下午2:24:45
 *
 */
public enum CashOperEnum {

	ACCEPT((byte) 0x02), // 受理
	SUCCESS((byte) 0x03), // 成功
	FAILURE((byte) 0x04); // 失败
	private Byte val;

	CashOperEnum(Byte val) {
		this.val = val;
	}

	public static CashOperEnum getEnum(Byte val) {
		CashOperEnum[] values = CashOperEnum.values();
		for (CashOperEnum object : values) {
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
