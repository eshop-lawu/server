package com.lawu.eshop.product.constant;

/**
 * @author zhangyong
 * @date 2018/4/18.
 */
public enum  ProductCategoryTypeEnum {
    PRODUCT_CATEGORY_HOT((byte) 0x01),        //热门
    PRODUCT_CATEGORY_NORMAL((byte) 0x00);    //普通

    private Byte val;
    ProductCategoryTypeEnum(Byte val){
        this.val = val;
    }

    public static ProductCategoryTypeEnum getEnum(Byte val){
        ProductCategoryTypeEnum[] values = ProductCategoryTypeEnum.values();
        for (ProductCategoryTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }
}
