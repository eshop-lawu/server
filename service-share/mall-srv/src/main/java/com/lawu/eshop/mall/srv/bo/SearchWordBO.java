package com.lawu.eshop.mall.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/4/7.
 */
public class SearchWordBO {

    private Long id;

    private String word;

    private Byte type;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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
