package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/20.
 */
public class ProductCityDTO {

    @ApiModelProperty(value = "城市id", required = true)
    private Long cityId;

    @ApiModelProperty(value = "城市名称", required = true)
    private String cityName;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
