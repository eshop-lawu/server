package com.lawu.eshop.user.srv.bo;

/**
 * @author zhangyong
 * @date 2018/1/19.
 */
public class AbnormalAccountBO {
    private Long id;

    private String userNum;

    private String account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
