package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 生成大额红包BO
 * 
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
public class GenerateLargeRedpacketBO {

    /**
     * 预期最小红包总金额
     */
    private BigDecimal expectedMinRedpacketAmount;

    /**
     * 预期最大红包总金额
     */
    private BigDecimal expectedMaxRedpacketAmount;
    
    /**
     * 大额红包总金额
     */
    private BigDecimal totalManualAmount;

    /**
     * 红包列表
     */
    List<GenerateRedpacketBO> redpacketList;

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

    public List<GenerateRedpacketBO> getRedpacketList() {
        return redpacketList;
    }

    public void setRedpacketList(List<GenerateRedpacketBO> redpacketList) {
        this.redpacketList = redpacketList;
    }

}
