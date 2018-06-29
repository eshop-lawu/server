package com.lawu.eshop.operator.srv.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class RolePermissionDOView implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String permissionName;

    private String permissionKey;

    private String permissionUrl;

    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
