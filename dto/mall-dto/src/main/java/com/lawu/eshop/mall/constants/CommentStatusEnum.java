package com.lawu.eshop.mall.constants;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
public enum CommentStatusEnum {
    COMMENT_STATUS_VALID((byte) 0x01),//有效
    COMMENT_STATUS_INVALID((byte) 0x00);//无效
    public Byte val;

    CommentStatusEnum(Byte val) {
        this.val = val;
    }

    public static CommentStatusEnum getEnum(Byte val) {
        CommentStatusEnum[] values = CommentStatusEnum.values();
        for (CommentStatusEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
