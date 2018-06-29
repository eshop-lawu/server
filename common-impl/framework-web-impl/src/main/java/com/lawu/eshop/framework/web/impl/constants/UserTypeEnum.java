package com.lawu.eshop.framework.web.impl.constants;

import com.lawu.eshop.idworker.client.impl.BizIdType;

/**
 * 用户类型
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public enum UserTypeEnum {
	
	/**
	 * 2-会员
	 */
	MEMBER((byte)0x02, BizIdType.MEMBER.getPrefix()),
	
	/**
	 * 1-商家
	 */
	MERCHANT((byte)0x01, BizIdType.MERCHANT.getPrefix());
	
	private Byte value;
	
	/**
	 * 用户编号前缀
	 */
	private String prefix;

	private UserTypeEnum(Byte value, String prefix) {
		this.value = value;
		this.prefix = prefix;
	}
	
    public Byte getValue() {
		return value;
	}

	public String getPrefix() {
		return prefix;
	}

	public static UserTypeEnum getEnum(Byte val) {
    	UserTypeEnum[] values = UserTypeEnum.values();
        for (UserTypeEnum object : values) {
            if (object.getValue().equals(val)) {
                return object;
            }
        }
        return null;
    }
	
	/**
	 * 根据用户编号获取用户类型
	 * @param userNum
	 * @return
	 * @author Sunny
	 * @date 2017年7月3日
	 */
	public static UserTypeEnum getEnum(String userNum) {
		String prefix = String.valueOf(userNum.charAt(0));
		UserTypeEnum[] values = UserTypeEnum.values();
        for (UserTypeEnum object : values) {
            if (object.getPrefix().equals(prefix)) {
                return object;
            }
        }
        return null;
    }
}
