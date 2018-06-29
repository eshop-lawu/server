package com.lawu.eshop.product.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/4/18.
 */
public class ProductCategoryHotDTO {

    private List<ProductCategoryListDTO> categoryList;

    public List<ProductCategoryListDTO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ProductCategoryListDTO> categoryList) {
        this.categoryList = categoryList;
    }
}
