package com.lawu.eshop.common.constants;

/**
 * 用户类型枚举类
 * @author zhangrc
 * @date 2017/12/21
 *
 */
public enum UserNameEnum {

    MEMBER((byte) 0x02, "E店用户"),      //用户
    MERCHANT((byte) 0x01, "E店商家");     //商家
    private Byte val;
    private String name;

    UserNameEnum(Byte val,String name) {
        this.val = val;
        this.name = name;
    }

    public static UserNameEnum getEnum(Byte val) {
        UserNameEnum[] values = UserNameEnum.values();
        for (UserNameEnum object : values) {
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
