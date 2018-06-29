package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.activity.constants.DistributionStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 钻石分配记录DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
@ApiModel(description = "钻石分配记录DTO")
public class DiamondDistributionRecordDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 分配的居民数量
     */
    @ApiModelProperty(value = "分配的居民数量", required = true)
    private Long residents;

    /**
     * 已分配的居民数量
     */
    @ApiModelProperty(value = "已分配的居民数量", required = true)
    private Long allocatedResidents;

    /**
     * 预期分配E钻数量
     */
    @ApiModelProperty(value = "预期分配E钻数量", required = true)
    private BigDecimal expectedOrdinaryAllocations;

    /**
     * 用户实际分配E钻数量
     */
    @ApiModelProperty(value = "用户实际分配E钻数量", required = true)
    private BigDecimal realOrdinaryAllocations;
    
    /**
     * 商家实际分配E钻数量
     */
    @ApiModelProperty(value = "商家实际分配E钻数量", required = true)
    private BigDecimal merchantRealOrdinaryAllocations;

    /**
     * 分配幸运钻的居民数量
     */
    @ApiModelProperty(value = "分配幸运钻的居民数量", required = true)
    private Long luckyResidents;

    /**
     * 已分配幸运钻的居民数量
     */
    @ApiModelProperty(value = "已分配幸运钻的居民数量", required = true)
    private Long allocatedLuckyResidents;

    /**
     * 预期分配幸运钻数量
     */
    @ApiModelProperty(value = "预期分配幸运钻数量", required = true)
    private BigDecimal expectedLuckyAllocations;

    /**
     * 用户实际分配幸运钻数量
     */
    @ApiModelProperty(value = "用户实际分配幸运钻数量", required = true)
    private BigDecimal realLuckAllocations;
    
    /**
     * 商家实际分配幸运钻数量
     */
    @ApiModelProperty(value = "商家实际分配幸运钻数量", required = true)
    private BigDecimal merchantRealLuckAllocations;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态(ALLOCATION-分配中|ALLOCATED-已分配|ISSUED-已发放)", required = true)
    private DistributionStatusEnum status;

    /**
     * 分配完成时间
     */
    @ApiModelProperty(value = "分配完成时间", required = true)
    private Date gmtAllocationsComplete;

    /**
     * 发放完成时间
     */
    @ApiModelProperty(value = "发放完成时间", required = true)
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