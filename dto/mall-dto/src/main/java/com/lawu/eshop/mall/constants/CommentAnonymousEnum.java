package com.lawu.eshop.mall.constants;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
public enum CommentAnonymousEnum {
    COMMENT_ANONYMOUS((boolean) true),//匿名
    UN_COMMENT_ANONYMOUS((boolean) false);//不匿名

    public Boolean val;

    CommentAnonymousEnum(Boolean val) {
        this.val = val;
    }

    public static CommentAnonymousEnum getEnum(Boolean val) {
        CommentAnonymousEnum[] values = CommentAnonymousEnum.values();
        for (CommentAnonymousEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
