package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.AttendTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK上线参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
public class PuzzlePkOnlineParam {

    /**
     * 参与类型
     */
    @ApiModelProperty(value = "参与类型")
    private AttendTypeEnum attendType;

    /**
     * 是否房主
     */
    @ApiModelProperty(value = "是否房主")
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
