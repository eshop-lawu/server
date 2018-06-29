package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.activity.constants.DistributionStatusEnum;

/**
 * 钻石分配记录BO
 * 
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
public class DiamondDistributionRecordBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 分配的居民数量
     */
    private Long residents;

    /**
     * 已分配的居民数量
     */
    private Long allocatedResidents;

    /**
     * 预期分配E钻数量
     */
    private BigDecimal expectedOrdinaryAllocations;

    /**
     * 用户实际分配E钻数量
     */
    private BigDecimal realOrdinaryAllocations;
    
    /**
     * 商家实际分配E钻数量
     */
    private BigDecimal merchantRealOrdinaryAllocations;

    /**
     * 分配幸运钻的居民数量
     */
    private Long luckyResidents;

    /**
     * 已分配幸运钻的居民数量
     */
    private Long allocatedLuckyResidents;

    /**
     * 预期分配幸运钻数量
     */
    private BigDecimal expectedLuckyAllocations;

    /**
     * 用户实际分配幸运钻数量
     */
    private BigDecimal realLuckAllocations;
    
    /**
     * 商家实际分配幸运钻数量
     */
    private BigDecimal merchantRealLuckAllocations;

    /**
     * 状态
     */
    private DistributionStatusEnum status;

    /**
     * 分配完成时间
     */
    private Date gmtAllocationsComplete;

    /**
     * 发放完成时间
     */
    private Date gmtGrantComplete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResidents() {
        return residents;
    }

    public void setResidents(Long residents) {
        this.residents = residents;
    }

    public Long getAllocatedResidents() {
        return allocatedResidents;
    }

    public void setAllocatedResidents(Long allocatedResidents) {
        this.allocatedResidents = allocatedResidents;
    }

    public BigDecimal getExpectedOrdinaryAllocations() {
        return expectedOrdinaryAllocations;
    }

    public void setExpectedOrdinaryAllocations(BigDecimal expectedOrdinaryAllocations) {
        this.expectedOrdinaryAllocations = expectedOrdinaryAllocations;
    }

    public BigDecimal getRealOrdinaryAllocations() {
        return realOrdinaryAllocations;
    }

    public void setRealOrdinaryAllocations(BigDecimal realOrdinaryAllocations) {
        this.realOrdinaryAllocations = realOrdinaryAllocations;
    }

    public BigDecimal getMerchantRealOrdinaryAllocations() {
        return merchantRealOrdinaryAllocations;
    }

    public void setMerchantRealOrdinaryAllocations(BigDecimal merchantRealOrdinaryAllocations) {
        this.merchantRealOrdinaryAllocations = merchantRealOrdinaryAllocations;
    }

    public Long getLuckyResidents() {
        return luckyResidents;
    }

    public void setLuckyResidents(Long luckyResidents) {
        this.luckyResidents = luckyResidents;
    }

    public Long getAllocatedLuckyResidents() {
        return allocatedLuckyResidents;
    }

    public void setAllocatedLuckyResidents(Long allocatedLuckyResidents) {
        this.allocatedLuckyResidents = allocatedLuckyResidents;
    }

    public BigDecimal getExpectedLuckyAllocations() {
        return expectedLuckyAllocations;
    }

    public void setExpectedLuckyAllocations(BigDecimal expectedLuckyAllocations) {
        this.expectedLuckyAllocations = expectedLuckyAllocations;
    }

    public BigDecimal getRealLuckAllocations() {
        return realLuckAllocations;
    }

    public void setRealLuckAllocations(BigDecimal realLuckAllocations) {
        this.realLuckAllocations = realLuckAllocations;
    }

    public BigDecimal getMerchantRealLuckAllocations() {
        return merchantRealLuckAllocations;
    }

    public void setMerchantRealLuckAllocations(BigDecimal merchantRealLuckAllocations) {
        this.merchantRealLuckAllocations = merchantRealLuckAllocations;
    }

    public DistributionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DistributionStatusEnum status) {
        this.status = status;
    }

    public Date getGmtAllocationsComplete() {
        return gmtAllocationsComplete;
    }

    public void setGmtAllocationsComplete(Date gmtAllocationsComplete) {
        this.gmtAllocationsComplete = gmtAllocationsComplete;
    }

    public Date getGmtGrantComplete() {
        return gmtGrantComplete;
    }

    public void setGmtGrantComplete(Date gmtGrantComplete) {
        this.gmtGrantComplete = gmtGrantComplete;
    }
    
}