package com.lawu.eshop.operator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
public class PermissionListDTO {

    @ApiModelProperty(value = "权限ID")
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "权限键值")
    private String permissionKey;

    @ApiModelProperty(value = "权限URL")
    private String permissionUrl;

    private Integer parentId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
