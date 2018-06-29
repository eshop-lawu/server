package com.lawu.eshop.mall.srv.bo;

/**
 * @author meishuquan
 * @date 2017/3/27
 */
public class SmsRecordBO {

    private Long id;

    private String content;

    private Long virifyCodeId;

    public Long getVirifyCodeId() {
        return virifyCodeId;
    }

    public void setVirifyCodeId(Long virifyCodeId) {
        this.virifyCodeId = virifyCodeId;
    }

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
