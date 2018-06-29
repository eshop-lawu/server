package com.lawu.eshop.mall.constants;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
public enum CommentTypeEnum {
    COMMENT_TYPE_MERCHANT((byte) 0x01),//商家
    COMMENT_TYPE_PRODUCT((byte) 0x02);//商品
    public Byte val;

    CommentTypeEnum(Byte val) {
        this.val = val;
    }

    public static CommentTypeEnum getEnum(Byte val) {
        CommentTypeEnum[] values = CommentTypeEnum.values();
        for (CommentTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
