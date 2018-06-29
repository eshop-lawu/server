package com.lawu.eshop.mall.constants;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
public enum SearchWordTypeEnum {

    WORD_TYPE_STORE((byte) 0x01),     //门店词条
    WORD_TYPE_PRODUCT((byte) 0x02);    //商品词条
    public Byte val;

    SearchWordTypeEnum(Byte val) {
        this.val = val;
    }

    public static SearchWordTypeEnum getEnum(Byte val) {
        SearchWordTypeEnum[] values = SearchWordTypeEnum.values();
        for (SearchWordTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
