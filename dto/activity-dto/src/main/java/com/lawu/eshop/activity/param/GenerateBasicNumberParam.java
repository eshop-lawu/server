package com.lawu.eshop.activity.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 生成基础号码参数
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
@ApiModel
public class GenerateBasicNumberParam {
    
    /**
     * 开奖日收盘时的上证指数
     */
    @NotNull(message = "开奖日收盘时的上证指数不能为空")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "开奖日收盘时的上证指数为保留两位小数的数字")
    @ApiModelProperty(value = "开奖日收盘时的上证指数", required = true)
    private String shanghaiCompositeIndex;
    
    /**
     * 开奖日收盘时的深证成指
     */
    @NotNull(message = "开奖日收盘时的深证成指不能为空")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "开奖日收盘时的深证成指为保留两位小数的数字")
    @ApiModelProperty(value = "开奖日收盘时的深证成指", required = true)
    private String shenzhenComponentIndex;

    public String getShanghaiCompositeIndex() {
        return shanghaiCompositeIndex;
    }

    public void setShanghaiCompositeIndex(String shanghaiCompositeIndex) {
        this.shanghaiCompositeIndex = shanghaiCompositeIndex;
    }

    public String getShenzhenComponentIndex() {
        return shenzhenComponentIndex;
    }

    public void setShenzhenComponentIndex(String shenzhenComponentIndex) {
        this.shenzhenComponentIndex = shenzhenComponentIndex;
    }
    
}
