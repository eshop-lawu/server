package com.lawu.eshop.game.param;

import javax.validation.constraints.NotNull;

import com.lawu.eshop.game.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK上线参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
@ApiModel("头脑PK上线参数")
public class MindPkOnlineParam {

    /**
     * 参与类型
     */
    @NotNull(message = "参与类型不能为空")
    @ApiModelProperty(value = "参与类型")
    private AttendTypeEnum attendType;

    /**
     * 是否房主, 随机匹配需要这个参数
     */
    @ApiModelProperty(value = "是否房主, 随机匹配需要这个参数")
    private Boolean isHomeowner;

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }

    public Boolean getIsHomeowner() {
        return isHomeowner;
    }

    public void setIsHomeowner(Boolean isHomeowner) {
        this.isHomeowner = isHomeowner;
    }
    
}
