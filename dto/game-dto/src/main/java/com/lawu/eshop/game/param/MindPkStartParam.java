package com.lawu.eshop.game.param;

import javax.validation.constraints.NotNull;

import com.lawu.eshop.game.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK开始参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
@ApiModel("头脑PK开始参数")
public class MindPkStartParam {

    /**
     * 参与类型
     */
    @NotNull(message = "参与类型不能为空")
    @ApiModelProperty(value = "参与类型", required = true)
    private AttendTypeEnum attendType;

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }
    
}
