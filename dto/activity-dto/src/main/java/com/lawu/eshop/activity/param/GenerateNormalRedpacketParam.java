package com.lawu.eshop.activity.param;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 生成大额红包参数
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
@ApiModel
public class GenerateNormalRedpacketParam {
    
    /**
     * 自动分配红包总额
     */
    @NotNull(message = "自动分配红包总额不能为空")
    @DecimalMin(value = "0.01", message = "自动分配红包总额不能小于0.01")
    @ApiModelProperty(value = "自动分配红包总额", required = true)
    private BigDecimal totalAutoAmount;
    
    public BigDecimal getTotalAutoAmount() {
        return totalAutoAmount;
    }

    public void setTotalAutoAmount(BigDecimal totalAutoAmount) {
        this.totalAutoAmount = totalAutoAmount;
    }
}
