package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public class AppPatchVersionParam {

    @ApiModelProperty(value = "app_version_id", required = true)
    private Long appVersionId;

    @ApiModelProperty(value = "app版本", required = true)
    private String appVersion;

    @ApiModelProperty(value = "补丁版本", required = true)
    private Integer patchVersion;

    @ApiModelProperty(value = "更新内容", required = true)
    private String updateDesc;

    @ApiModelProperty(value = "状态", required = true)
    private AppPatchVersionStatusEnum statusEnum;

    public Long getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(Long appVersionId) {
        this.appVersionId = appVersionId;
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
}
