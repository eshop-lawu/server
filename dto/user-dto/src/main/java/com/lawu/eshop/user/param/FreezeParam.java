package com.lawu.eshop.user.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.user.constants.FreezeTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 冻结参数
 * @author jiangxinjun
 * @createDate 2018年1月18日
 * @updateDate 2018年1月18日
 */
@ApiModel
public class FreezeParam {

    /**
     * 用户编号
     */
    @NotBlank(message = "用户编号不能为空")
    @ApiModelProperty(value = "用户编号", required = true)
    private String num;

    /**
     * 是否冻结
     */
    @NotNull(message = "是否冻结字段不能为空")
    @ApiModelProperty(value = "是否冻结", required = true)
    private Boolean isFreeze;

    /**
     * 冻结类型
     */
    @NotNull(message = "冻结类型不能为空")
    @ApiModelProperty(value = "冻结类型", required = true)
    private FreezeTypeEnum freezeType;
    
    /**
     * 冻结原因
     */
    @ApiModelProperty(value = "冻结原因")
    private String freezeReason;
    
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean freeze) {
        isFreeze = freeze;
    }

    public FreezeTypeEnum getFreezeType() {
        return freezeType;
    }

    public void setFreezeType(FreezeTypeEnum freezeType) {
        this.freezeType = freezeType;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }
}
