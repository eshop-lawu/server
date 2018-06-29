package com.lawu.eshop.product.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/20.
 */
public class ProductShipmentsPlaceDTO {

    @ApiModelProperty(value = "商品发货地", required = true)
    private List<ProductCityDTO> cityDTOS;

    public List<ProductCityDTO> getCityDTOS() {
        return cityDTOS;
    }

    public void setCityDTOS(List<ProductCityDTO> cityDTOS) {
        this.cityDTOS = cityDTOS;
    }
}
