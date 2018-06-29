package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductSpecOptionListDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "规格ID")
    private Long productSpecId;

    @ApiModelProperty(value = "规格项名称")
    private String optionName;

    @ApiModelProperty(value = "图标")
    private String iconUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
