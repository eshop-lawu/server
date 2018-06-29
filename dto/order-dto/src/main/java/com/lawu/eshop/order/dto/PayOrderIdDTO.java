package com.lawu.eshop.order.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/13.
 */
public class PayOrderIdDTO {

    @ApiModelProperty(value = "订单编号")
    private String orderNum;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
