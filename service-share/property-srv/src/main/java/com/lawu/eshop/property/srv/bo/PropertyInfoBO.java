package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2017/3/27
 */
public class PropertyInfoBO {

    private String userNum;

    private BigDecimal balance;

    private BigDecimal point;

    private BigDecimal loveAccount;

    private Byte freeze;

    private String payPassword;

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public Byte getFreeze() {
        return freeze;
    }

    public void setFreeze(Byte freeze) {
        this.freeze = freeze;
    }
}
