package com.lawu.eshop.product.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public class OperatorProductSpecParam extends AbstractPageParam{
    private Integer productCategoryId;

    private String specName;

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
}
