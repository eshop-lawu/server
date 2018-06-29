package com.lawu.eshop.activity.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 钻石分配参数
 * @author jiangxinjun
 * @createDate 2018年5月3日
 * @updateDate 2018年5月3日
 */
public class DiamondDistributionParam {
    
    /**
     * 主键
     */
    private Long id;
    
    /**
     * E钻数量
     */
    private BigDecimal diamond;
    
    /**
     * 幸运钻数量
     */
    private BigDecimal diamondLucky;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDiamond() {
        return diamond;
    }

    public void setDiamond(BigDecimal diamond) {
        this.diamond = diamond;
    }

    public BigDecimal getDiamondLucky() {
        return diamondLucky;
    }

    public void setDiamondLucky(BigDecimal diamondLucky) {
        this.diamondLucky = diamondLucky;
    }

}
