package com.lawu.eshop.ad.constants;


/**
 * 红包支付方式
 * @author lihj
 * @date 2017年8月11号
 */
public enum UserRedPacketPayTypeEnum {
	BALANCE((byte) 0x01,"余额"),
	ALIPAY((byte) 0x02,"支付宝"),
	WX((byte) 0x03,"微信");
	private Byte val;
	private String name;
	
	UserRedPacketPayTypeEnum(Byte val,String name){
		this.val=val;
		this.name=name;
	}

	public static UserRedPacketPayTypeEnum getEnum(Byte val) {
		UserRedPacketPayTypeEnum[] values = UserRedPacketPayTypeEnum.values();
		for (UserRedPacketPayTypeEnum object : values) {
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
