package com.lawu.eshop.operator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public class UserListDTO {

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
