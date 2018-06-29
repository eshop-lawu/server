package com.lawu.eshop.product.param;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public class ProductBrandParam {

    private Integer productCategoryId;

    private String brandName;

    private Integer ordinal;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}
