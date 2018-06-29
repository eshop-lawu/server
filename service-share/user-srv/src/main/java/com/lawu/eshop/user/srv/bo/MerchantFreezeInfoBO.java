package com.lawu.eshop.user.srv.bo;

/**
 * @author meishuquan
 * @date 2017/12/19.
 */
public class MerchantFreezeInfoBO {

    private String account;

    private String userNum;

    private String freezeReason;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }
}
