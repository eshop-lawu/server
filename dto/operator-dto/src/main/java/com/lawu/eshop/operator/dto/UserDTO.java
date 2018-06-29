package com.lawu.eshop.operator.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class UserDTO {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;
    private List<UserRoleDTO> roles = new ArrayList<>();

    private Set<String> rolesKey = new HashSet<>();

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleDTO> roles) {
        this.roles = roles;
    }

    public Set<String> getRolesKey() {
        return rolesKey;
    }

    public void setRolesKey(Set<String> rolesKey) {
        this.rolesKey = rolesKey;
    }
}
