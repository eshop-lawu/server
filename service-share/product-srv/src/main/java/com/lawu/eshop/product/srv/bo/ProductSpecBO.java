package com.lawu.eshop.product.srv.bo;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductSpecBO {

    private Long id;

    private Integer productCategoryId;

    private String specName;

    private Integer ordinal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
