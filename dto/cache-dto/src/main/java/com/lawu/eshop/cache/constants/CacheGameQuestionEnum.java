package com.lawu.eshop.cache.constants;

/**
 * @author zhangyong
 * @date 2018/3/29.
 */
public enum CacheGameQuestionEnum {
    SIMPLE((byte) 0x01, "全部为简单题目"),
    DIFFICULT((byte) 0x02, "全部为困难题目"),
    OTHER((byte) 0x01, "两者都有");

    private Byte val;
    private String name;

    CacheGameQuestionEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static CacheGameQuestionEnum getEnum(Byte val) {
        CacheGameQuestionEnum[] values = CacheGameQuestionEnum.values();
        for (CacheGameQuestionEnum object : values) {
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
