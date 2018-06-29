package com.lawu.eshop.product.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/23.
 */
public class MerchantProductCategoryDTO {

    @ApiModelProperty(value = "商品类别")
    private List<ProductCategoryTierDTO> tierDTOS;

    public List<ProductCategoryTierDTO> getTierDTOS() {
        return tierDTOS;
    }

    public void setTierDTOS(List<ProductCategoryTierDTO> tierDTOS) {
        this.tierDTOS = tierDTOS;
    }
}
