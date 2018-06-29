package com.lawu.eshop.activity.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 统计钻石分配数量DO
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
public class StatisticsDiamondNumberDO {
    
    /**
     * 分配的总人数
     */
    private Long count;
    
    /**
     * 分配的钻石总数量
     */
    private BigDecimal diamond;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getDiamond() {
        return diamond;
    }

    public void setDiamond(BigDecimal diamond) {
        this.diamond = diamond;
    }
    
}
