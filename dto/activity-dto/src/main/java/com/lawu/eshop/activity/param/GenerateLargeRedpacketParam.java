package com.lawu.eshop.activity.param;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 生成大额红包参数
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
@ApiModel
public class GenerateLargeRedpacketParam {
    
    /**
     * 生成红包方案
     */
    @NotNull(message = "生成红包方案不能为空")
    @Size(min = 1, message = "只要需要一条生成红包方案")
    @ApiModelProperty(value = "生成红包方案", required = true)
    List<GenerateRedpacketParam> list;

    public List<GenerateRedpacketParam> getList() {
        return list;
    }

    public void setList(List<GenerateRedpacketParam> list) {
        this.list = list;
    }
    
}
