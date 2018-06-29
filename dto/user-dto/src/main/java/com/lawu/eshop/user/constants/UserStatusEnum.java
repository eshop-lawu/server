package com.lawu.eshop.user.constants;

/**
 * @author meishuquan
 * @date 2017/3/29.
 */
public enum UserStatusEnum {

    MEMBER_STATUS_NOVALID((byte) 0x00),      //无效
    MEMBER_STATUS_VALID((byte) 0x01);     //有效
    public Byte val;

    UserStatusEnum(Byte val) {
        this.val = val;
    }

    public static UserStatusEnum getEnum(Byte val) {
        UserStatusEnum[] values = UserStatusEnum.values();
        for (UserStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
