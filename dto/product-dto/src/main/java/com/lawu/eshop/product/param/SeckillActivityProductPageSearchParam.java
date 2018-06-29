package com.lawu.eshop.product.param;

import com.lawu.eshop.product.constant.ActivityProductStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 运营平台分页查询参数
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月27日
 */
@ApiModel
public class SeckillActivityProductPageSearchParam extends AbstractPageParam {
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private ActivityProductStatusEnum status;

    public ActivityProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ActivityProductStatusEnum status) {
        this.status = status;
    }
    
}
