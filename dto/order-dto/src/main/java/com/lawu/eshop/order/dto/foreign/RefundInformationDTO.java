package com.lawu.eshop.order.dto.foreign;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 退款所需要展示的信息
 * @author jiangxinjun
 * @createDate 2018年3月22日
 * @updateDate 2018年3月22日
 */
@ApiModel("退款所需要展示的信息")
public class RefundInformationDTO {
    
    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额", required = true)
    private BigDecimal amount;
    
    /**
     * 退还积分
     */
    @ApiModelProperty(value = "退还积分", required = true)
    private BigDecimal point;

    /**
     * 实际退款金额
     */
    @ApiModelProperty(value = "实际退款金额", required = true)
    private BigDecimal actualAmount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
    
}
