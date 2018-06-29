package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/13.
 */
public class ProductSearchWordDTO {

    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "数量", required = true)
    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
