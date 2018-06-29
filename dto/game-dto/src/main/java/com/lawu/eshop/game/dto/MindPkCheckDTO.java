package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK检查游戏信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月19日
 * @updateDate 2018年3月20日
 */
@ApiModel("头脑PK检查返回DTO")
public class MindPkCheckDTO {
    
    /**
     * 是否所有人积分扣除成功
     */
    @ApiModelProperty(value = "是否所有人积分扣除成功", required = true)
    private Boolean isSuccess;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    
}
