package com.lawu.eshop.ad.constants;


/**
 * 广告支付方式
 * @author zhangrc
 * @date 2017年10月19号
 */
public enum AdPayTypeEnum {
	POINT((byte) 0x01,"积分"),
	ALIPAY((byte) 0x02,"支付宝"),
	WX((byte) 0x03,"微信");
	private Byte val;
	private String name;
	
	AdPayTypeEnum(Byte val,String name){
		this.val=val;
		this.name=name;
	}

	public static AdPayTypeEnum getEnum(Byte val) {
		AdPayTypeEnum[] values = AdPayTypeEnum.values();
		for (AdPayTypeEnum object : values) {
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
