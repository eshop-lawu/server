package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 生成大额红包DTO
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
@ApiModel
public class GenerateLargeRedpacketDTO {
    
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
     * 大额红包总金额
     */
    @ApiModelProperty(value = "大额红包总金额", required = true)
    private BigDecimal totalManualAmount;
    
    /**
     * 红包列表
     */
    @ApiModelProperty(value = "红包列表", required = true)
    List<GenerateRedpacketDTO> redpacketList;

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

    public BigDecimal getTotalManualAmount() {
        return totalManualAmount;
    }

    public void setTotalManualAmount(BigDecimal totalManualAmount) {
        this.totalManualAmount = totalManualAmount;
    }

    public List<GenerateRedpacketDTO> getRedpacketList() {
        return redpacketList;
    }

    public void setRedpacketList(List<GenerateRedpacketDTO> redpacketList) {
        this.redpacketList = redpacketList;
    }
    
}
