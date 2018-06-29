package com.lawu.eshop.user.constants;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
public enum RongOnlineStatusEnum {
    STATUS_ONLINE((String) "1"),      //在线
    STATUS_OFFLINE((String) "0");     //离线
    public String val;

    RongOnlineStatusEnum(String val) {
        this.val = val;
    }

    public static RongOnlineStatusEnum getEnum(String val) {
        RongOnlineStatusEnum[] values = RongOnlineStatusEnum.values();
        for (RongOnlineStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
