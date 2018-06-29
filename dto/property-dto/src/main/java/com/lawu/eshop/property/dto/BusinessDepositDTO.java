package com.lawu.eshop.property.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BusinessDepositDTO implements Serializable {

    private Long id;

    private Long businessId;

    private String userNum;

    private String businessAccount;

    private String businessName;

    private String depositNumber;

    private String thirdNumber;

    private String thirdAccount;

    private Byte paymentMethod;

    private BigDecimal amount;

    private Byte status;

    private Long businessBankAccountId;

    private Long provinceId;

    private Long cityId;

    private Long areaId;

    private Date gmtCreate;

    private Date gmtPay;

    private Date gmtVerify;

    private Date gmtRefund;

    private Date gmtAccpet;

    private Date gmtModified;

    private Long operUserId;

    private String operUserName;

    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getBusinessAccount() {
        return businessAccount;
    }

    public void setBusinessAccount(String businessAccount) {
        this.businessAccount = businessAccount;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public String getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public String getThirdAccount() {
        return thirdAccount;
    }

    public void setThirdAccount(String thirdAccount) {
        this.thirdAccount = thirdAccount;
    }

    public Byte getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Byte paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getBusinessBankAccountId() {
        return businessBankAccountId;
    }

    public void setBusinessBankAccountId(Long businessBankAccountId) {
        this.businessBankAccountId = businessBankAccountId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPay() {
        return gmtPay;
    }

    public void setGmtPay(Date gmtPay) {
        this.gmtPay = gmtPay;
    }

    public Date getGmtVerify() {
        return gmtVerify;
    }

    public void setGmtVerify(Date gmtVerify) {
        this.gmtVerify = gmtVerify;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtAccpet() {
        return gmtAccpet;
    }

    public void setGmtAccpet(Date gmtAccpet) {
        this.gmtAccpet = gmtAccpet;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Long operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}