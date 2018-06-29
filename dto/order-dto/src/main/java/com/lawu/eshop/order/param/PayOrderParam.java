package com.lawu.eshop.order.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public class PayOrderParam {

    @ApiModelProperty(value = "商家ID",required = true)
    private Long merchantId;

    /**
     * 原价
     */
    @ApiModelProperty(value = "原价" ,required = true)
    private double totalAmount;

    @ApiModelProperty(value = "不参与优惠金额" ,required = true)
    private double notFavoredAmount;

    @ApiModelProperty(value = "优惠金额",required = true)
    private double favoredAmount;

    @ApiModelProperty(value = "商家编号",required = true)
    private String merchantNum;

    @ApiModelProperty(value = "商家优惠ID")
    private Long merchantFavoredId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantFavoredId() {
        return merchantFavoredId;
    }

    public void setMerchantFavoredId(Long merchantFavoredId) {
        this.merchantFavoredId = merchantFavoredId;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getNotFavoredAmount() {
        return notFavoredAmount;
    }

    public void setNotFavoredAmount(double notFavoredAmount) {
        this.notFavoredAmount = notFavoredAmount;
    }

    public double getFavoredAmount() {
        return favoredAmount;
    }

    public void setFavoredAmount(double favoredAmount) {
        this.favoredAmount = favoredAmount;
    }
}
