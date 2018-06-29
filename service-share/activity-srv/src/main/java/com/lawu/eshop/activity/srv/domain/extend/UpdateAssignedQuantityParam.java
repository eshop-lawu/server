package com.lawu.eshop.activity.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 更新分配数量参数
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public class UpdateAssignedQuantityParam {
    
    /**
     * 记录id
     */
    private Long id;
    
    /**
     * 分配E钻的居民数量
     */
    private Long allocatedResidents;
    
    /**
     * 用户实际分配E钻数量
     */
    private BigDecimal realOrdinaryAllocations;
    
    /**
     * 商家实际分配E钻数量
     */
    private BigDecimal merchantRealOrdinaryAllocations;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAllocatedResidents() {
        return allocatedResidents;
    }

    public void setAllocatedResidents(Long allocatedResidents) {
        this.allocatedResidents = allocatedResidents;
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
    
}
