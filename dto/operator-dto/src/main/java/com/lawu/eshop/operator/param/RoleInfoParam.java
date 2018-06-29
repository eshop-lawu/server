package com.lawu.eshop.operator.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
public class RoleInfoParam {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色键值")
    private String roleKey;

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
}
