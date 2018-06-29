package com.lawu.eshop.product.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductSpecDTO {

    private List<ProductSpecListDTO> specList;

    public List<ProductSpecListDTO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<ProductSpecListDTO> specList) {
        this.specList = specList;
    }
}
