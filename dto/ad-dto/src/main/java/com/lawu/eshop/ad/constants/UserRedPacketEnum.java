/**
 * 
 */
package com.lawu.eshop.ad.constants;

/**
 * @author lihj
 * @date 2017年8月7日
 */
public enum UserRedPacketEnum {

	USER_STATUS_UNPAID((byte) 0x00, "未支付"),
	USER_STATUS_EFFECTIVE((byte) 0x01, "有效"),
	USER_STATUS_OVER((byte) 0x02, "已领完"),
	USER_STATUS_OUT((byte) 0x03, "过期");

	public Byte val;
	private String name;

	UserRedPacketEnum(Byte val, String name) {
		this.val = val;
		this.name = name;
	}

	public static UserRedPacketEnum getEnum(Byte val) {
		UserRedPacketEnum[] values = UserRedPacketEnum.values();
		for (UserRedPacketEnum object : values) {
			if (object.val.equals(val)) {
				return object;
			}
		}
		return null;
	}
	
	public static String getName(Byte val) {
		UserRedPacketEnum[] values = UserRedPacketEnum.values();
		for (UserRedPacketEnum object : values) {
			if (object.val.equals(val)) {
				return object.name;
			}
		}
		return null;
	}

}
