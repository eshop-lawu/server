package com.lawu.eshop.product.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public class OperatorProductBrandParam extends AbstractPageParam{

    private Integer productCategoryId;

    private String brandName;

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
}
