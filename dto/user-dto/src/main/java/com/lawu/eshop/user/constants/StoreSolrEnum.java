package com.lawu.eshop.user.constants;

/**
 * @author meishuquan
 * @date 2017/4/13.
 */
public enum StoreSolrEnum {

    INTELLIGENT_SORT((byte) 0x00), //智能
    DISTANCE_SORT((byte) 0x01),    //距离
    FEEDBACK_SORT((byte) 0x02),    //好评
    POPULARITY_SORT((byte) 0x03);  //人气
    public Byte val;

    StoreSolrEnum(Byte val) {
        this.val = val;
    }

    public static StoreSolrEnum getEnum(Byte val) {
        StoreSolrEnum[] values = StoreSolrEnum.values();
        for (StoreSolrEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }
}
