package com.lawu.eshop.activity.param;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 生成红包参数
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
@ApiModel
public class GenerateRedpacketParam {
    
    /**
     * 生成方案下标
     */
    @NotNull(message = "生成方案下标不能为空")
    @ApiModelProperty(value = "生成方案下标", required = true)
    private Integer redpacketIdx;
    
    /**
     * 红包额度
     */
    @DecimalMin(value = "0.01", message = "最大红包额度不能小于0.01")
    @ApiModelProperty(value = "红包额度", required = true)
    private BigDecimal redpacketAmount;
    
    /**
     * 红包数量
     */
    @DecimalMin(value = "1", message = "最大红包额度不能小于1")
    @ApiModelProperty(value = "红包数量", required = true)
    private Integer redpacketQuantity;

    public Integer getRedpacketIdx() {
        return redpacketIdx;
    }

    public void setRedpacketIdx(Integer redpacketIdx) {
        this.redpacketIdx = redpacketIdx;
    }

    public BigDecimal getRedpacketAmount() {
        return redpacketAmount;
    }

    public void setRedpacketAmount(BigDecimal redpacketAmount) {
        this.redpacketAmount = redpacketAmount;
    }

    public Integer getRedpacketQuantity() {
        return redpacketQuantity;
    }

    public void setRedpacketQuantity(Integer redpacketQuantity) {
        this.redpacketQuantity = redpacketQuantity;
    }
    
}
