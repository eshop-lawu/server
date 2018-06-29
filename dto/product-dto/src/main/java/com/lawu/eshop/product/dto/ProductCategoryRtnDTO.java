package com.lawu.eshop.product.dto;


import java.util.List;

public class ProductCategoryRtnDTO {

    private List<ProductCategoryDTO> productCategoryDTOList;

    public List<ProductCategoryDTO> getProductCategoryDTOList() {
        return productCategoryDTOList;
    }

    public void setProductCategoryDTOList(List<ProductCategoryDTO> productCategoryDTOList) {
        this.productCategoryDTOList = productCategoryDTOList;
    }
}
