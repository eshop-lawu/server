package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 保证金操作
 * </p>
 * @author Yangqh
 * @date 2017年4月15日 下午3:59:54
 *
 */
public enum BusinessDepositOperEnum {

	VERIFYD((byte) 0x02),	//核实
	ACCPET_REFUND((byte) 0x04),//受理
	REFUND_SUCCESS((byte) 0x05),//成功
	REFUND_FAILURE((byte) 0x06);//失败
	private Byte val;

	BusinessDepositOperEnum(Byte val) {
		this.val = val;
	}

	public static BusinessDepositOperEnum getEnum(Byte val) {
		BusinessDepositOperEnum[] values = BusinessDepositOperEnum.values();
		for (BusinessDepositOperEnum object : values) {
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
