package com.lawu.eshop.activity.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 更新分配数量参数
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public class UpdateIssueQuantityParam {
    
    /**
     * 记录id
     */
    private Long id;
    
    /**
     * 已发放的小鸡数量
     */
    private Long grantChicks;
    
    /**
     * 已发放金蛋数量
     */
    private BigDecimal grant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrantChicks() {
        return grantChicks;
    }

    public void setGrantChicks(Long grantChicks) {
        this.grantChicks = grantChicks;
    }

    public BigDecimal getGrant() {
        return grant;
    }

    public void setGrant(BigDecimal grant) {
        this.grant = grant;
    }
    
}
