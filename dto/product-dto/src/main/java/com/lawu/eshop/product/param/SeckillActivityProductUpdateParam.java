package com.lawu.eshop.product.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动商品更新参数
 * 
 * @author jiangxinjun
 * @createDate 2018年5月23日
 * @updateDate 2018年5月23日
 */
@ApiModel(description = "抢购活动商品更新参数")
public class SeckillActivityProductUpdateParam {

    /**
     * 排序优先级
     */
    @NotNull
    @Min(0)
    @ApiModelProperty(value = "排序优先级", required = true)
    private Integer priority;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
}