package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/15.
 */
public class MessageTemplateBO {
    private Integer id;

    /**
     *
     * 标题
     * message_template.title
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private String title;

    /**
     *
     * 模板内容
     * message_template.content
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    /**
     *
     * 消息类型
     * message_template.type

     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Byte type;

    /**
     *
     * 修改时间
     * message_template.gmt_modified
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * message_template.gmt_create
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Date gmtCreate;
}
