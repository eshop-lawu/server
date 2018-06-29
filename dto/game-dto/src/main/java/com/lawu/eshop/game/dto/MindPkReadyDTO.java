package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK准备DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月16日
 * @updateDate 2018年3月19日
 */
@ApiModel("头脑PK准备DTO")
public class MindPkReadyDTO {
    
    /**
     * 当前准备的用户编号
     */
    @ApiModelProperty(value = "当天准备的用户编号", required = true)
    private String readyUserNum;

    public String getReadyUserNum() {
        return readyUserNum;
    }

    public void setReadyUserNum(String readyUserNum) {
        this.readyUserNum = readyUserNum;
    }
    
}
