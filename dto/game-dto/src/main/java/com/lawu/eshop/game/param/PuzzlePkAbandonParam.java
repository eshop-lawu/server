package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.AttendTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj
 * @date 2018/3/20
 */
@ApiModel("拼图PK退出参数")
public class PuzzlePkAbandonParam {

    @ApiModelProperty(value = "参与类型", required = true)
    private AttendTypeEnum attendType;

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }
}
