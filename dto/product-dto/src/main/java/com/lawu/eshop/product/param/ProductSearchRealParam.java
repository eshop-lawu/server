package com.lawu.eshop.product.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public class ProductSearchRealParam extends AbstractPageParam{

    private Long productId;

    private Integer categoryId;

    private String name;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
