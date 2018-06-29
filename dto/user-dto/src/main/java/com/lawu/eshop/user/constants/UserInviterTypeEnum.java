package com.lawu.eshop.user.constants;

/**
 * @author meishuquan
 * @date 2017/3/29
 */
public enum UserInviterTypeEnum {

    INVITER_TYPE_NULL((byte) 0x00),       //无推荐
    INVITER_TYPE_MEMBER((byte) 0x01),     //会员推荐
    INVITER_TYPE_MERCHANT((byte) 0x02);    //商户推荐
    public Byte val;

    UserInviterTypeEnum(Byte val) {
        this.val = val;
    }

    public static UserInviterTypeEnum getEnum(Byte val) {
        UserInviterTypeEnum[] values = UserInviterTypeEnum.values();
        for (UserInviterTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
