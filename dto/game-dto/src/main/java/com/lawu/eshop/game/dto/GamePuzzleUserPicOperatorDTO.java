package com.lawu.eshop.game.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
public class GamePuzzleUserPicOperatorDTO {

    private Long id;

    private String userNickname;

    private String imgPath;

    private Boolean isSimple;

    private Boolean isCommon;

    private Boolean isHard;

    private GamePuzzleUserPicStatusEnum statusEnum;

    private String statusDes;

    private String hardLevel;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GamePuzzleUserPicStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GamePuzzleUserPicStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public String getHardLevel() {
        return hardLevel;
    }

    public void setHardLevel(String hardLevel) {
        this.hardLevel = hardLevel;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
