package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

/**
 * 站内信息统计BO
 * Created by zhangyong on 2017/3/29.
 */
public class MessageStatisticsBO {

    /**
     * 信息内容
     */
    private  String content;

    private  Byte type;
    private Date gmtCreate;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
