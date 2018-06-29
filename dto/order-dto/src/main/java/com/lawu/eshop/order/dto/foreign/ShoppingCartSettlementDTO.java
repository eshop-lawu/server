package com.lawu.eshop.order.dto.foreign;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 购物车结算DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月10日
 * @updateDate 2018年3月10日
 */
@ApiModel
public class ShoppingCartSettlementDTO {

    /**
     * 总计
     */
    @ApiModelProperty(value = "总计", required = true)
    private BigDecimal total;

    /**
     * 用户余额
     */
    @ApiModelProperty(value = "用户余额", required = true)
    private BigDecimal balance;
    
    /**
     * 用户积分
     */
    @ApiModelProperty(value = "用户积分", required = true)
    private BigDecimal point;

    /**
     * 积分兑换比例
     */
    @ApiModelProperty(value = "积分兑换比例", required = true)
    private BigDecimal exchangeRate;
    
    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id", required = true)
    private Long activityId;

    /**
     * 活动商品id
     */
    @ApiModelProperty(value = "活动商品id", required = true)
    private Long activityProductId;

    /**
     * 活动商品型号id
     */
    @ApiModelProperty(value = "活动商品型号id", required = true)
    private Long activityProductModelId;

    /**
     * 订单数据 - 分单显示
     */
    @ApiModelProperty(value = "订单数据", required = true)
    private List<ShoppingCartSettlementItemDTO> items;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }
    
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityProductId() {
        return activityProductId;
    }

    public void setActivityProductId(Long activityProductId) {
        this.activityProductId = activityProductId;
    }

    public Long getActivityProductModelId() {
        return activityProductModelId;
    }

    public void setActivityProductModelId(Long activityProductModelId) {
        this.activityProductModelId = activityProductModelId;
    }

    public List<ShoppingCartSettlementItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartSettlementItemDTO> items) {
        this.items = items;
    }

}