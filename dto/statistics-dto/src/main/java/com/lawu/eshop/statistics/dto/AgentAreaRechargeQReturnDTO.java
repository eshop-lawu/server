package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author yangqh
 * @date 2017/8/15.
 */
public class AgentAreaRechargeQReturnDTO {

    @ApiModelProperty(value = "省ID")
    private Integer provinceId;

    @ApiModelProperty(value = "市ID")
    private Integer cityId;

    @ApiModelProperty(value = "区ID")
    private Integer areaId;

    @ApiModelProperty(value = "商家充值余额")
    private BigDecimal merchantRechargeBalance;

    @ApiModelProperty(value = "商家充值积分")
    private BigDecimal merchantRechargePoint;

    @ApiModelProperty(value = "用户充值余额")
    private BigDecimal memberRechargeBalance;

    @ApiModelProperty(value = "用户充值积分")
    private BigDecimal memberRechargePoint;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getMerchantRechargeBalance() {
        return merchantRechargeBalance;
    }

    public void setMerchantRechargeBalance(BigDecimal merchantRechargeBalance) {
        this.merchantRechargeBalance = merchantRechargeBalance;
    }

    public BigDecimal getMerchantRechargePoint() {
        return merchantRechargePoint;
    }

    public void setMerchantRechargePoint(BigDecimal merchantRechargePoint) {
        this.merchantRechargePoint = merchantRechargePoint;
    }

    public BigDecimal getMemberRechargeBalance() {
        return memberRechargeBalance;
    }

    public void setMemberRechargeBalance(BigDecimal memberRechargeBalance) {
        this.memberRechargeBalance = memberRechargeBalance;
    }

    public BigDecimal getMemberRechargePoint() {
        return memberRechargePoint;
    }

    public void setMemberRechargePoint(BigDecimal memberRechargePoint) {
        this.memberRechargePoint = memberRechargePoint;
    }
}
