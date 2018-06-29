package com.lawu.eshop.agent.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/10.
 */
public class AgentUserListParam extends AbstractPageParam {

    @ApiModelProperty(value = "账号")
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
