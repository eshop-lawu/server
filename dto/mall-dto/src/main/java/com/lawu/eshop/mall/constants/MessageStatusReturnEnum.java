package com.lawu.eshop.mall.constants;

/**
 * @author zhangyong
 * @date 2017/4/12.
 */
public enum MessageStatusReturnEnum {
    MESSAGE_STATUS_UNREAD((byte) 0x00),
    MESSAGE_STATUS_READ((byte) 0x01);
    public Byte val;

    MessageStatusReturnEnum(Byte val) {
        this.val = val;
    }

    public static MessageStatusReturnEnum getEnum(Byte val) {
        MessageStatusReturnEnum[] values = MessageStatusReturnEnum.values();
        for (MessageStatusReturnEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
