package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProductSpecOptionJsonDTO {

    @ApiModelProperty(value = "规格选项ID")
    private Long id;

    @ApiModelProperty(value = "规格项名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "修改商品回显时判断是否选中")
    private boolean isSelected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
