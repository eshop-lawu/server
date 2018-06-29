package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PropertyInfoDOEiditView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userNum;
    private BigDecimal balance;
    private BigDecimal point;
    private BigDecimal loveAccount;
    private BigDecimal freezeMoney;
    private Date gmtModified;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getLoveAccount() {
        return loveAccount;
    }

    public void setLoveAccount(BigDecimal loveAccount) {
        this.loveAccount = loveAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }
}