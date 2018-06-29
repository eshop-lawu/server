package com.lawu.eshop.user.param;

/**
 * @author meishuquan
 * @date 2017/9/11.
 */
public class UserFreezeRecordParam {

    private String userNum;

    private String account;

    private Byte userType;

    private String cause;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
