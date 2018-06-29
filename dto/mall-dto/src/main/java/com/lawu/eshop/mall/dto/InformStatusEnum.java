/**
 * 
 */
package com.lawu.eshop.mall.dto;

/**
 * @author lihj
 * @date 2017年7月27日
 */
public enum InformStatusEnum {

	INFORM_PENDING((byte) 0x00, "待处理"), 
	INFORM_ALREADY_PROCESSED((byte) 0x01, "已经处理"),
	INFORM_NOT_HANDLED((byte) 0x02,	"不处理");

	private Byte val;

	private String name;

	InformStatusEnum(Byte val, String name) {
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

	public static InformStatusEnum getEnum(Byte val) {
		InformStatusEnum[] values = InformStatusEnum.values();
		for (InformStatusEnum object : values) {
			if (object.val == val) {
				return object;
			}
		}
		return null;
	}
}
