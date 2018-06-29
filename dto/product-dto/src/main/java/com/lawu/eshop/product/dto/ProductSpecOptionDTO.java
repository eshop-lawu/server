package com.lawu.eshop.product.dto;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductSpecOptionDTO {

    private List<ProductSpecOptionListDTO> optionList;

    public List<ProductSpecOptionListDTO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<ProductSpecOptionListDTO> optionList) {
        this.optionList = optionList;
    }
}
