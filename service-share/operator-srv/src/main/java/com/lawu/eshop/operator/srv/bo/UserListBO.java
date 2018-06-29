package com.lawu.eshop.operator.srv.bo;

import com.lawu.eshop.operator.constants.StatusEnum;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public class UserListBO {

    private Integer id;

    private String account;

    private String name;

    private Date gmtCreate;

    private StatusEnum statusEnum;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
