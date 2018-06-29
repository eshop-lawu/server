package com.lawu.eshop.cache.constants;

public enum CacheGameTypeEnum {
	
	MIND((byte) 0x01, "头脑PK"), 
	PUZZLE((byte) 0x02, "拼图");

	private Byte val;
	private String name;

	CacheGameTypeEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}

	public static CacheGameTypeEnum getEnum(Byte val) {
		CacheGameTypeEnum[] values = CacheGameTypeEnum.values();
		for (CacheGameTypeEnum object : values) {
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
