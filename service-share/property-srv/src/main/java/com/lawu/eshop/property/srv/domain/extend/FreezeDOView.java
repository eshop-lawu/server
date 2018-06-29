package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FreezeDOView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal money;

    private BigDecimal previousMoney;

    private Date gmtModified;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreviousMoney() {
        return previousMoney;
    }

    public void setPreviousMoney(BigDecimal previousMoney) {
        this.previousMoney = previousMoney;
    }
}