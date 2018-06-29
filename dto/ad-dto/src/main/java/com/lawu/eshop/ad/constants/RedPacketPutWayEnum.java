package com.lawu.eshop.ad.constants;

public enum RedPacketPutWayEnum {

	PUT_WAY_COMMON((byte) 0x01, "普通红包"), // 普通
	PUT_WAY_LUCK((byte) 0x02, "拼手气红包"); // 手气
	
	public Byte val;
	public String name;

	RedPacketPutWayEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}

	public static RedPacketPutWayEnum getEnum(Byte val) {
		RedPacketPutWayEnum[] values = RedPacketPutWayEnum.values();
		for (RedPacketPutWayEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}

	public static String getName(Byte val) {
		RedPacketPutWayEnum[] values = RedPacketPutWayEnum.values();
		for (RedPacketPutWayEnum object : values) {
			if (object.val.equals(val)) {
				return object.name;
			}
		}
		return null;
	}

}
