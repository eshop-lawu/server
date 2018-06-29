package com.lawu.eshop.product.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductBrandDTO {

    private List<ProductBrandListDTO> brandList;

    public List<ProductBrandListDTO> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<ProductBrandListDTO> brandList) {
        this.brandList = brandList;
    }
}
