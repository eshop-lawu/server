package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/5/10.
 */
public class CommentSignBO {
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
