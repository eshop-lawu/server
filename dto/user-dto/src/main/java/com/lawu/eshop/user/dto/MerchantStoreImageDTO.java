package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/11.
 */
public class MerchantStoreImageDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "图片类别")
    private MerchantStoreImageEnum merchantStoreImageEnum;

    @ApiModelProperty(value = "图片路径")
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MerchantStoreImageEnum getMerchantStoreImageEnum() {
        return merchantStoreImageEnum;
    }

    public void setMerchantStoreImageEnum(MerchantStoreImageEnum merchantStoreImageEnum) {
        this.merchantStoreImageEnum = merchantStoreImageEnum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
