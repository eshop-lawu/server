package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2018/1/19.
 */
public class AbnormalAccountDOView implements Serializable {
    private static final long serialVersionUID = -5570370647021895542L;

    private String account;

    private String userNum;

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
