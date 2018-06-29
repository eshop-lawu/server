package com.lawu.eshop.game.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class GamePuzzleUserPicBO {

    private Long id;

    private String userNum;

    private String userNickname;

    private String imgPath;

    private Byte type;

    private Boolean isSimple;

    private Boolean isCommon;

    private Boolean isHard;

    private Byte status;

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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getIsSimple() {
        return isSimple;
    }

    public void setIsSimple(Boolean simple) {
        isSimple = simple;
    }

    public Boolean getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(Boolean common) {
        isCommon = common;
    }

    public Boolean getIsHard() {
        return isHard;
    }

    public void setIsHard(Boolean hard) {
        isHard = hard;
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
}
