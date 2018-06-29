package com.lawu.eshop.mall.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public class AppPatchVersionOperatorDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "APP版本")
    private String appVersion;

    @ApiModelProperty(value = "补丁版本")
    private Integer patchVersion;

    @ApiModelProperty(value = "更新内容")
    private String updateDesc;

    @ApiModelProperty(value = "状态")
    private AppPatchVersionStatusEnum statusEnum;

    @ApiModelProperty(value = "状态描述")
    private String statusDes;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(Integer patchVersion) {
        this.patchVersion = patchVersion;
    }

    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }

    public AppPatchVersionStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(AppPatchVersionStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
