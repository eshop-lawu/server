package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;

import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 助力红包活动BO
 * 
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
@ApiModel
public class HelpRedpacketActivityDTO extends HelpRedpacketActivityUpdateParam {
    
    /**
     * 手动分配红包总额
     */
    @ApiModelProperty(value = "手动分配红包总额", required = false)
    private BigDecimal totalManualAmount;
    
    /**
     * 自动分配红包总额
     */
    @ApiModelProperty(value = "自动分配红包总额", required = false)
    private BigDecimal totalAutoAmount;
    
    /**
     * 预期最小红包总金额
     */
    @ApiModelProperty(value = "预期最小红包总金额", required = true)
    private BigDecimal expectedMinRedpacketAmount;

    /**
     * 预期最大红包总金额
     */
    @ApiModelProperty(value = "预期最大红包总金额", required = true)
    private BigDecimal expectedMaxRedpacketAmount;
    
    /**
     * 疑似异常账号分配总金额
     */
    @ApiModelProperty(value = "疑似异常账号分配总金额", required = true)
    private BigDecimal abnormalRedpacketTotalAmount;
    
    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态(NOT_STARTED-未开始|REGISTING-报名中|REGIST_END-报名结束|BEGINNING-开抢中|END-已结束)", required = true)
    private HelpRedpacketActivityStatusEnum status;

    public BigDecimal getTotalManualAmount() {
        return totalManualAmount;
    }

    public void setTotalManualAmount(BigDecimal totalManualAmount) {
        this.totalManualAmount = totalManualAmount;
    }
    
    public BigDecimal getTotalAutoAmount() {
        return totalAutoAmount;
    }

    public void setTotalAutoAmount(BigDecimal totalAutoAmount) {
        this.totalAutoAmount = totalAutoAmount;
    }
    
    public BigDecimal getExpectedMinRedpacketAmount() {
        return expectedMinRedpacketAmount;
    }

    public void setExpectedMinRedpacketAmount(BigDecimal expectedMinRedpacketAmount) {
        this.expectedMinRedpacketAmount = expectedMinRedpacketAmount;
    }

    public BigDecimal getExpectedMaxRedpacketAmount() {
        return expectedMaxRedpacketAmount;
    }

    public void setExpectedMaxRedpacketAmount(BigDecimal expectedMaxRedpacketAmount) {
        this.expectedMaxRedpacketAmount = expectedMaxRedpacketAmount;
    }

    public BigDecimal getAbnormalRedpacketTotalAmount() {
        return abnormalRedpacketTotalAmount;
    }

    public void setAbnormalRedpacketTotalAmount(BigDecimal abnormalRedpacketTotalAmount) {
        this.abnormalRedpacketTotalAmount = abnormalRedpacketTotalAmount;
    }

    public HelpRedpacketActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HelpRedpacketActivityStatusEnum status) {
        this.status = status;
    }
    
}