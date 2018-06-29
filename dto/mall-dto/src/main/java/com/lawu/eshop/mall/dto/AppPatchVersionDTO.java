package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public class AppPatchVersionDTO {

    @ApiModelProperty(value = "APP补丁版本下载地址")
    private String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
