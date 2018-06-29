package com.lawu.eshop.operator.srv.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class UserRoleDOView implements Serializable{

    private static final long serialVersionUID = 1L;

    private String roleName;

    private String roleKey;

    private Integer id;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
