package com.lawu.eshop.game.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class GamePuzzleUserPicDTO {

    @ApiModelProperty(value = "图片路径")
    private String imgPath;

    @ApiModelProperty(value = "简单")
    private Boolean isSimple;

    @ApiModelProperty(value = "一般")
    private Boolean isCommon;

    @ApiModelProperty(value = "困难")
    private Boolean isHard;

    @ApiModelProperty(value = "状态：CHECK_PENDING--待审核，HAVE_USE--已采用，NOT_USE--未采用")
    private GamePuzzleUserPicStatusEnum statusEnum;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
