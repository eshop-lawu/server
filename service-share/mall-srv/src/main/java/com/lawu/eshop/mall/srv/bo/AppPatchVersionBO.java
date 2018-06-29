package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public class AppPatchVersionBO {

    private Long id;

    private Long appVersionId;

    private String appVersion;

    private Integer patchVersion;

    private String updateDesc;

    private Byte status;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
