package com.lawu.eshop.product.srv.domain.extend;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/23.
 */
public class ProductCategoryItemDOView {

    @ApiModelProperty(value = "商品类别ID")
    private Integer id;

    @ApiModelProperty(value = "商品类别名称")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
