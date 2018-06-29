package com.lawu.eshop.property.constants;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月24日 下午8:20:56
 *
 */
public enum LoveTypeEnum {

	AD_CLICK((byte) 0x01, "看广告"),
	AD_COMMISSION((byte) 0x02, "广告提成"),
	SALES_COMMISSION((byte) 0x03, "推荐E友收益"),
	VOLUME_COMMISSION((byte) 0x04, "推荐商家收益"),
	AD_QZ((byte) 0x05, "抢赞"),
	RED_PACKAGE((byte) 0x06, "红包"),
	MEMBER_FANS((byte) 0x07, "成为粉丝");

	private Byte value;

	private String name;

	LoveTypeEnum(Byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public Byte getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}