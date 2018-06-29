package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class GamePuzzleUserPicUploadNumberDTO {

    @ApiModelProperty(value = "上传次数")
    private Integer uploadNumber;

    @ApiModelProperty(value = "采用次数")
    private Integer usedNumber;

    public Integer getUploadNumber() {
        return uploadNumber;
    }

    public void setUploadNumber(Integer uploadNumber) {
        this.uploadNumber = uploadNumber;
    }

    public Integer getUsedNumber() {
        return usedNumber;
    }

    public void setUsedNumber(Integer usedNumber) {
        this.usedNumber = usedNumber;
    }
}
