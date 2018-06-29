package com.lawu.eshop.order.dto.foreign;

import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单状态DTO
 * @author jiangxinjun
 * @createDate 2018年4月26日
 * @updateDate 2018年4月26日
 */
@ApiModel("订单状态DTO")
public class ShoppingOrderStatusDTO {
	
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态|PENDING 待处理|PENDING_PAYMENT 待付款|BE_SHIPPED 待发货|TO_BE_RECEIVED 待收货|TRADING_SUCCESS 交易成功|CANCEL_TRANSACTION 交易关闭|REFUNDING 退款中", required = true)
    private ShoppingOrderStatusEnum orderStatus;

    public ShoppingOrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(ShoppingOrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }
    
}