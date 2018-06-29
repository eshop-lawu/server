package com.lawu.eshop.game.param;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("踢人参数")
public abstract class AbstractPKRejectParam {
    
    @NotBlank(message = "被踢的用户编号不能为空")
    @ApiModelProperty(value = "被踢的用户编号", required = true)
    private String kickedUserNum;

    public String getKickedUserNum() {
        return kickedUserNum;
    }

    public void setKickedUserNum(String kickedUserNum) {
        this.kickedUserNum = kickedUserNum;
    }
}
