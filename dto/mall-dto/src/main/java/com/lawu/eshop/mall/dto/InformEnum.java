/**
 * 
 */
package com.lawu.eshop.mall.dto;

/**
 * @author lihj
 * @date 2017年7月27日
 */
public enum InformEnum {

	INFORM_TYPE_PLAT((byte)0x01, "平面广告"), 
	INFORM_TYPE_PRAISE((byte)0x02, "E咻"),
	INFORM_TYPE_MERCHANT((byte)0x03, "商家"), 
	INFORM_TYPE_GOODS((byte)0x04, "商品"),
	INFORM_TYPE_VIDEO((byte)0x05,"视频");

	private Byte val;

	private String name;

	InformEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}

	/**
	 * @return the val
	 */
	public Byte getVal() {
		return val;
	}

	/**
	 * @param val
	 *            the val to set
	 */
	public void setVal(Byte val) {
		this.val = val;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public static InformEnum getEnum(Byte val) {
		InformEnum[] values = InformEnum.values();
		for (InformEnum object : values) {
			if (object.val == val) {
				return object;
			}
		}
		return null;
	}
}
