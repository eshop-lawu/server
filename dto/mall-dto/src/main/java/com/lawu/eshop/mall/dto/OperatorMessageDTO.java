package com.lawu.eshop.mall.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MessageTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/27.
 */
public class OperatorMessageDTO {
    @ApiModelProperty(value = "消息ID")
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息类型")
    private MessageTypeEnum messageType;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

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

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
