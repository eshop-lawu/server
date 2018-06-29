package com.lawu.eshop.operator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
public class RoleDTO {

    @ApiModelProperty(value = "角色ID")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色键值")
    private String roleKey;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
