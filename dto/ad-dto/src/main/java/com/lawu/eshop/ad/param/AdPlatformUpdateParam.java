package com.lawu.eshop.ad.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月7日
 */
@ApiModel(description = "广告位更新参数")
public class AdPlatformUpdateParam {

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