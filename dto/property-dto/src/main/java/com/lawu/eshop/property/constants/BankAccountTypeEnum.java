package com.lawu.eshop.property.constants;

public enum BankAccountTypeEnum {
	ALL((byte) 0x00, "all","全部"),
	BANK((byte) 0x01,"bank","银行卡"),
	ALIPAY((byte) 0x02,"alipay","支付宝"),
	WEIXIN((byte) 0x03,"weixin","微信");
	private Byte val;
	private String key;
	private String name;

	BankAccountTypeEnum(Byte val,String key, String name) {
		this.val = val;
		this.key = key;
		this.name = name;
	}

	public static BankAccountTypeEnum getEnum(Byte val) {
		BankAccountTypeEnum[] values = BankAccountTypeEnum.values();
		for (BankAccountTypeEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public Byte getVal() {
		return val;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

}
