package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/3/27
 */
public class SmsRecordDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "短信内容")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
