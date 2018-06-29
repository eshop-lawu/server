package com.lawu.eshop.game.param;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public class UserStartParam {

    private Long id;

    private Integer starCount;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
