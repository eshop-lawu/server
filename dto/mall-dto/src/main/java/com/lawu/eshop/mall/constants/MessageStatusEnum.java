package com.lawu.eshop.mall.constants;

/**
 * 信息状态枚举类
 * Created by zhangyong on 2017/3/29.
 */
public enum MessageStatusEnum {

    MESSAGE_STATUS_UNREAD((byte) 0x00),
    MESSAGE_STATUS_READ((byte) 0x01),
    MESSAGE_STATUS_DELETE((byte) 0x02);
    public Byte val;

    MessageStatusEnum(Byte val) {
        this.val = val;
    }

    public static MessageStatusEnum getEnum(Byte val) {
        MessageStatusEnum[] values = MessageStatusEnum.values();
        for (MessageStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

}
