package com.lawu.eshop.operator.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public class UserPageParam extends AbstractPageParam{
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
