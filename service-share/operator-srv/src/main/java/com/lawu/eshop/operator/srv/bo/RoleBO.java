package com.lawu.eshop.operator.srv.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class RoleBO {


    private String roleName;

    private String roleKey;

    private Integer id;

    private Date gmtCreate;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
