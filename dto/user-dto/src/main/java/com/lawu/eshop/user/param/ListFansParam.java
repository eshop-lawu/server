package com.lawu.eshop.user.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
public class ListFansParam extends AbstractPageParam {

    @ApiModelProperty(value = "区域路径")
    private String regionPath;

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

}
