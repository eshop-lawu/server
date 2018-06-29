package com.lawu.eshop.operator.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public class PerssionParam {

    @ApiModelProperty(value = "权限名称")
    private String permissionName;
    @ApiModelProperty(value = "权限键值")
    private String permissionKey;
    @ApiModelProperty(value = "权限URL")
    private String permissionUrl;
    @ApiModelProperty(value = "父节点ID")
    private Integer parentId;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
