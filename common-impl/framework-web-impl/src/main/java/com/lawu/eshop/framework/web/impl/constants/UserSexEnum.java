package com.lawu.eshop.framework.web.impl.constants;

/**
 * @author zhangyong
 * @date 2017/8/26.
 */
public enum UserSexEnum {
    SEX_MALE((byte) 0x00),      //男
    SEX_SECRET((byte) 0x01),     //保密
    SEX_FEMALE((byte) 0x02);    //女
    private Byte val;

    UserSexEnum(Byte val) {
        this.val = val;
    }

    public static UserSexEnum getEnum(Byte val) {
        UserSexEnum[] values = UserSexEnum.values();
        for (UserSexEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }
}
