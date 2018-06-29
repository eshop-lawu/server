package com.lawu.eshop.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.order.constants.EvaluationEnum;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public class PayOrderDTO {

    @ApiModelProperty(value = "买单ID")
    private Long id;

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "原价")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "实际消费")
    private BigDecimal actualAmount;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal favoredAmount;

    @ApiModelProperty(value = "EVALUATION_SUCCESS：已评，UN_EVALUATION：未评")
    private EvaluationEnum evaluationEnum;

    @ApiModelProperty(value = "买单时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getFavoredAmount() {
        return favoredAmount;
    }

    public void setFavoredAmount(BigDecimal favoredAmount) {
        this.favoredAmount = favoredAmount;
    }

    public EvaluationEnum getEvaluationEnum() {
        return evaluationEnum;
    }

    public void setEvaluationEnum(EvaluationEnum evaluationEnum) {
        this.evaluationEnum = evaluationEnum;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
