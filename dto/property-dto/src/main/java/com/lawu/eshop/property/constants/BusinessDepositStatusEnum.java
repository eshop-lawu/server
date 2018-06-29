package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 商家保证金状态
 * 0待支付 1未核实 2已核实 3申请退款 4受理退款 5退款成功 6退款失败
 * </p>
 * @author Yangqh
 * @date 2017年4月15日 上午11:34:21
 *
 */
public enum BusinessDepositStatusEnum {

	PAYING((byte) 0x00,"待支付"),
	VERIFY((byte) 0x01,"待核实"),
	VERIFYD((byte) 0x02,"已核实"),
	APPLY_REFUND((byte) 0x03,"申请退款"),
	ACCPET_REFUND((byte) 0x04,"退款已受理"),
	REFUND_SUCCESS((byte) 0x05,"退款成功"),
	REFUND_FAILURE((byte) 0x06,"退款失败");
	
	private Byte val;
	private String name;

	BusinessDepositStatusEnum(Byte val,String name) {
		this.val = val;
		this.name = name;
	}

	public static BusinessDepositStatusEnum getEnum(Byte val) {
		BusinessDepositStatusEnum[] values = BusinessDepositStatusEnum.values();
		for (BusinessDepositStatusEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public Byte getVal() {
		return val;
	}

	public String getName() {
		return name;
	}

}
