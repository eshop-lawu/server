package com.lawu.eshop.product.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/18.
 */
public class OperatorSpecOptionListParam extends AbstractPageParam{

    @ApiModelProperty(value = "规格ID")
    private Long productSpecId;

    @ApiModelProperty(value = "规格项名称")
    private String optionName;

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
}
