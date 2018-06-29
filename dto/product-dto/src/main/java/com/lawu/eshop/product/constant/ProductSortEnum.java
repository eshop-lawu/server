package com.lawu.eshop.product.constant;

public enum ProductSortEnum {

    PRICE_ASC("price_d asc"),    //价格升序
    PRICE_DESC("price_d desc"),    //价格降序
    SALESVOLUME_DESC("salesVolume_i desc");    //销量降序


    private String val;

    ProductSortEnum(String val) {
        this.val = val;
    }

    public static ProductSortEnum getEnum(String val) {
        ProductSortEnum[] values = ProductSortEnum.values();
        for (ProductSortEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public String getVal() {
        return val;
    }

}
