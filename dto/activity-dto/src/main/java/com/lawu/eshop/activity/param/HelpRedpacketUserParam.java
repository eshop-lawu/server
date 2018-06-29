package com.lawu.eshop.activity.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HelpRedpacketUserParam extends AbstractPageParam {
    
    /**
     * 红包活动id
     */
    @ApiModelProperty(value = "红包活动id", required = true)
    private Integer activityId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    
}
