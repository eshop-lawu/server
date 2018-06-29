package com.lawu.eshop.operator.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Leach
 * @date 2017/5/9
 */
public class CurrentUserDTO {

    @ApiModelProperty(value = "账号")
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
