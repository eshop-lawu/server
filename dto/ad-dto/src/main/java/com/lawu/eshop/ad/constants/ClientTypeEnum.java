package com.lawu.eshop.ad.constants;


/**
 * 广告客户端支付类型
 * @author zhangrc
 * @date 2017年10月24号
 */
public enum ClientTypeEnum {
	MOBLIE((byte) 0x01,"手机"),
	PC((byte) 0x02,"pc");
	private Byte val;
	private String name;
	
	ClientTypeEnum(Byte val,String name){
		this.val=val;
		this.name=name;
	}

	public static ClientTypeEnum getEnum(Byte val) {
		ClientTypeEnum[] values = ClientTypeEnum.values();
		for (ClientTypeEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}
	
	public Byte getVal() {
		return val;
	}

	public void setVal(Byte val) {
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
