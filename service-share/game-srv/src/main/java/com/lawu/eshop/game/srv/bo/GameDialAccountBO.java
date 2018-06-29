package com.lawu.eshop.game.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialAccountBO {

    private Long id;

    private String userNum;

    private Integer freeCount;

    private Boolean isGetFree;

    private Date gmtModified;

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

    public Integer getFreeCount() {
        return freeCount;
    }

    public void setFreeCount(Integer freeCount) {
        this.freeCount = freeCount;
    }

    public Boolean getIsGetFree() {
        return isGetFree;
    }

    public void setIsGetFree(Boolean getFree) {
        isGetFree = getFree;
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
}
