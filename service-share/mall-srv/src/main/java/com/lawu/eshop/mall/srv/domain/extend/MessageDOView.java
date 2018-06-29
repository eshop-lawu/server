package com.lawu.eshop.mall.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信息扩展DO
 * Created by Administrator on 2017/3/30.
 */
public class MessageDOView implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     *
     * 主键
     * message.id
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Long id;

    /**
     *
     * 用户编号
     * message.user_num
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private String userNum;

    /**
     *
     * 关联主键
     * message.relate_id
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Long relateId;

    /**
     *
     * 消息类型
     * message.type
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Byte type;

    /**
     *
     * 消息内容
     * message.content
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private String content;

    /**
     *
     * 状态：0未读，1已读，2删除
     * message.status
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Byte status;

    /**
     *
     * 修改时间
     * message.gmt_modified
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * message.gmt_create
     *
     * @mbg.generated 2017-03-29 11:30:47
     */
    private Date gmtCreate;

    /**
     * 标题
     */
    private String title;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
