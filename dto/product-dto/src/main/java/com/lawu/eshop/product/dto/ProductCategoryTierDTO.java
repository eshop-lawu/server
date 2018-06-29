package com.lawu.eshop.product.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/23.
 */
public class ProductCategoryTierDTO extends ProductCategoryItemDTO {

    @ApiModelProperty(value = "商品子类别")
    private List<ProductCategoryItemDTO> itemDTOS;

    public List<ProductCategoryItemDTO> getItemDTOS() {
        return itemDTOS;
    }

    public void setItemDTOS(List<ProductCategoryItemDTO> itemDTOS) {
        this.itemDTOS = itemDTOS;
    }
}
