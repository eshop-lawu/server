package com.lawu.eshop.product.dto;

import java.util.List;

public class ProductAllRtnDTO {

   private List<ProductAllDTO> productAllDTOList;

    public List<ProductAllDTO> getProductAllDTOList() {
        return productAllDTOList;
    }

    public void setProductAllDTOList(List<ProductAllDTO> productAllDTOList) {
        this.productAllDTOList = productAllDTOList;
    }
}
