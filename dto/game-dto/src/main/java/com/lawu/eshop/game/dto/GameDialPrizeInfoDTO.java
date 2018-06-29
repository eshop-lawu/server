package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialPrizeInfoDTO {

    @ApiModelProperty(value = "奖品名称")
    private String name;

    @ApiModelProperty(value = "奖品图片")
    private String imgPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
