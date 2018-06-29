package com.lawu.eshop.product.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/4/17.
 */
public class OperatorProductCategoryDetailDTO {

    private ProductCategoryDTO categoryDTO;

    private List<ProductCategoryDTO> categoryDTOS;

    public ProductCategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(ProductCategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public List<ProductCategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(List<ProductCategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }
}
