package com.lawu.eshop.property.srv.domain.extend;/**
 * Created by ${Yangqh} on 2017/8/14.
 */

import java.math.BigDecimal;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2017/8/14 14:08
 */
public class ReportAreaRechargeDOExtend {

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private BigDecimal merchantRechargeBalance;

    private BigDecimal merchantRechargePoint;

    private BigDecimal memberRechargeBalance;

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
