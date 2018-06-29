package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReportAreaRechargeDailyDTO {

    private BigDecimal merchantRechargeBalance;

    private BigDecimal merchantRechargePoint;

    private BigDecimal memberRechargeBalance;

    private BigDecimal memberRechargePoint;

    private BigDecimal totalRechargeBalance;

    private BigDecimal totalRechargePoint;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private Date gmtReport;

    private Date gmtCreate;

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

    public BigDecimal getTotalRechargeBalance() {
        return totalRechargeBalance;
    }

    public void setTotalRechargeBalance(BigDecimal totalRechargeBalance) {
        this.totalRechargeBalance = totalRechargeBalance;
    }

    public BigDecimal getTotalRechargePoint() {
        return totalRechargePoint;
    }

    public void setTotalRechargePoint(BigDecimal totalRechargePoint) {
        this.totalRechargePoint = totalRechargePoint;
    }

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

    public Date getGmtReport() {
        return gmtReport;
    }

    public void setGmtReport(Date gmtReport) {
        this.gmtReport = gmtReport;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}