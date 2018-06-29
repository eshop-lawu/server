package com.lawu.eshop.activity.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 领取钻石参数
 * @author jiangxinjun
 * @createDate 2018年5月4日
 * @updateDate 2018年5月4日
 */
@ApiModel(description = "领取钻石参数")
public class ReceiveDiamondsParam {
    
    /**
     * 钻石收支记录id
     */
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "钻石收支记录id", required = true)
    private Long recordId;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    
}
