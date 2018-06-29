package com.lawu.eshop.activity.param;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 保存大额红包参数
 * @author jiangxinjun
 * @createDate 2017年12月30日
 * @updateDate 2017年12月30日
 */
@ApiModel
public class SaveRedpacketParam {
    
    /**
     * 助力红包活动参与详情id
     */
    @NotNull(message = "助力红包活动参与详情id不能为空")
    @ApiModelProperty(value = "助力红包活动参与详情id", required = true)
    private Long attendDetailId;
    
    /**
     * 自动生成红包方案index
     */
    @NotNull(message = "自动生成红包方案index不能为空")
    @ApiModelProperty(value = "自动生成红包方案index", required = true)
    private Integer generateLargeRedpacketIndex;

    public Long getAttendDetailId() {
        return attendDetailId;
    }

    public void setAttendDetailId(Long attendDetailId) {
        this.attendDetailId = attendDetailId;
    }

    public Integer getGenerateLargeRedpacketIndex() {
        return generateLargeRedpacketIndex;
    }

    public void setGenerateLargeRedpacketIndex(Integer generateLargeRedpacketIndex) {
        this.generateLargeRedpacketIndex = generateLargeRedpacketIndex;
    }
    
}
