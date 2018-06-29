package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/15.
 */
public class MessageTemplateDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "模板标题")
    private String title;

    @ApiModelProperty(value = "模板内容")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
