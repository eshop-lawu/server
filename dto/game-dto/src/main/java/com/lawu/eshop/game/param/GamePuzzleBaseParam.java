package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class GamePuzzleBaseParam {

    @ApiModelProperty(value = "图片路径")
    private String imgPath;

    @ApiModelProperty(value = "简单")
    private Boolean isSimple;

    @ApiModelProperty(value = "一般")
    private Boolean isCommon;

    @ApiModelProperty(value = "困难")
    private Boolean isHard;

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
}
