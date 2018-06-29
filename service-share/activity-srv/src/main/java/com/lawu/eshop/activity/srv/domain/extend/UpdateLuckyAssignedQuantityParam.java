package com.lawu.eshop.activity.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 更新幸运钻分配数量参数
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public class UpdateLuckyAssignedQuantityParam {
    
    /**
     * 记录id
     */
    private Long id;
    
    /**
     * 分配E钻的居民数量
     */
    private Long allocatedLuckyResidents;
    
    /**
     * 用户实际分配幸运钻数量
     */
    private BigDecimal realLuckyAllocations;
    
    /**
     * 商家实际分配幸运钻数量
     */
    private BigDecimal merchantRealLuckyAllocations;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getAllocatedLuckyResidents() {
        return allocatedLuckyResidents;
    }

    public void setAllocatedLuckyResidents(Long allocatedLuckyResidents) {
        this.allocatedLuckyResidents = allocatedLuckyResidents;
    }

    public BigDecimal getRealLuckyAllocations() {
        return realLuckyAllocations;
    }

    public void setRealLuckyAllocations(BigDecimal realLuckyAllocations) {
        this.realLuckyAllocations = realLuckyAllocations;
    }

    public BigDecimal getMerchantRealLuckyAllocations() {
        return merchantRealLuckyAllocations;
    }

    public void setMerchantRealLuckyAllocations(BigDecimal merchantRealLuckyAllocations) {
        this.merchantRealLuckyAllocations = merchantRealLuckyAllocations;
    }
    
}
