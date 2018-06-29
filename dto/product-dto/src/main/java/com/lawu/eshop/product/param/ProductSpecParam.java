package com.lawu.eshop.product.param;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public class ProductSpecParam {

    private Integer productCategoryId;

    private String specName;

    private Integer ordinal;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}
