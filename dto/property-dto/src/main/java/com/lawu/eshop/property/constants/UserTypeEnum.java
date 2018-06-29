package com.lawu.eshop.property.constants;

/**
 * 用户类型枚举类
 * @author Yangqh
 * @date 2017年4月5日 下午4:17:25
 *
 */
public enum UserTypeEnum {

    MEMBER((byte) 0x02, "M"),      //用户
    MERCHANT((byte) 0x01, "B"),     //商家
    MEMCHANT_PC((byte) 0x03,"");	//商家PC
    private Byte val;
    private String name;

    UserTypeEnum(Byte val,String name) {
        this.val = val;
        this.name = name;
    }

    public static UserTypeEnum getEnum(Byte val) {
        UserTypeEnum[] values = UserTypeEnum.values();
        for (UserTypeEnum object : values) {
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
