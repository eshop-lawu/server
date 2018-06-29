package com.lawu.eshop.mq.dto.user;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
public class MessagePushInfo implements Serializable{
	
	private static final long serialVersionUID = 3365568735263813470L;

	private Long messageId;

    private String title;

    private String userNum;

    private String content;

    private Byte userType;

    private String area;

    private byte messageType;

    private Long relateId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public byte getMessageType() {
        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }
}
