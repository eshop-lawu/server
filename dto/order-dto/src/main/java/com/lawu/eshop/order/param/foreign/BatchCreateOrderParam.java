package com.lawu.eshop.order.param.foreign;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 批量创建订单参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月10日
 * @updateDate 2018年3月10日
 */
@ApiModel(value = "批量创建订单参数")
public class BatchCreateOrderParam {
    
    /**
     * 地址id
     */
    @NotNull(message = "地址id不能为空")
    @ApiModelProperty(name = "addressId", value = "地址id")
    private Long addressId;
    
    /**
     * 抵扣积分
     */
    @DecimalMin(value = "0", message = "抵扣积分大于0")
    @ApiModelProperty(value = "抵扣积分(如果用户选择使用积分,传入积分)")
    private BigDecimal integral;
    
    /**
     * 多个创建订单参数
     */
    @NotNull(message = "settlementParams不能为空")
    @ApiModelProperty(name = "settlementParams", required = true, value = "多个创建订单参数")
    private List<ShoppingOrderSettlementForeignParam> settlementParams;
    
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public List<ShoppingOrderSettlementForeignParam> getSettlementParams() {
        return settlementParams;
    }

    public void setSettlementParams(List<ShoppingOrderSettlementForeignParam> settlementParams) {
        this.settlementParams = settlementParams;
    }
    
}