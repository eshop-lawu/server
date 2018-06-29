package com.lawu.eshop.cache.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj
 * @date 2018/5/10
 */
public class SysMaintainConfigParam {
    @ApiModelProperty(value = "内容")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
