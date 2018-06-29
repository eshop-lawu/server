package com.lawu.eshop.game.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialRecordInfoDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "奖品名称")
    private String name;

    @ApiModelProperty(value = "奖品图片")
    private String imgPath;

    @ApiModelProperty(value = "GET_LOTTERY--已中奖，TAKE_LOTTERY--待发放，SEND_LOTTERY--已发放")
    private GameDialRecordStatusEnum statusEnum;

    @ApiModelProperty(value = "抽奖时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public GameDialRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GameDialRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
